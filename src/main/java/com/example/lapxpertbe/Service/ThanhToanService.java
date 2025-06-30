package com.example.lapxpertbe.Service;

import com.example.lapxpertbe.DTO.MuaNgayDTO;
import com.example.lapxpertbe.DTO.SerialInfoDTO;
import com.example.lapxpertbe.DTO.ThanhToanOnlineDTO;
import com.example.lapxpertbe.Enity.*;
import com.example.lapxpertbe.Repository.*;
import com.example.lapxpertbe.enums.LoaiHoaDon;
import com.example.lapxpertbe.enums.TrangThaiDonHang;
import com.example.lapxpertbe.enums.TrangThaiSerialNumber;
import com.example.lapxpertbe.enums.TrangThaiThanhToan;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ThanhToanService {
    private final SerialNumberRepository serialRepo;
    private final HoaDonRepository hoaDonRepo;
    private final HoaDonChiTietRepository chiTietRepo;
    private final GioHangChiTietRepository gioHangChiTietRepository;
    private final NguoiDungRepository nguoiDungRepo;
    private final SerialNumberHoaDonChiTietRepository serialNumberHoaDonChiTietRepository;
    private final DiaChiRepository diaChiRepository;
    private final SanPhamChiTietRepository sanPhamChiTietRepository;
    private final PhieuGiamGiaRepository phieuGiamGiaRepository;
    private final HoaDonPhieuGiamGiaRepository hoaDonPhieuGiamGiaRepository;


    public List<SerialInfoDTO> laySerialNumberTamThoi(Long sanPhamChiTietId, int soLuong) {
        // Lấy danh sách serials có trạng thái AVAILABLE
        List<SerialNumber> serials = serialRepo.findTopNBySanPhamChiTietIdAndTrangThai(
                sanPhamChiTietId,
                TrangThaiSerialNumber.AVAILABLE.name(),
                soLuong
        );

        // Nếu số lượng serial không đủ, ném exception
        if (serials.size() < soLuong) {
            throw new RuntimeException("Không đủ serial cho sản phẩm " + sanPhamChiTietId);
        }

        // Chuyển đổi List<SerialNumber> sang List<SerialInfoDTO>
        return serials.stream()
                .map(sn -> new SerialInfoDTO(sn.getId(), sn.getSerialNumberValue()))
                .collect(Collectors.toList());
    }
    public HoaDon thanhToanOnline(Long gioHangId, ThanhToanOnlineDTO thongTinKhachHang, String phuongThucThanhToan) {
        if (thongTinKhachHang == null) {
            throw new IllegalArgumentException("Thông tin khách hàng không được null");
        }

        // Bước 1: Chuẩn bị địa chỉ giao hàng
        DiaChi diaChi = diaChiRepository
                .findFirstByDuongAndPhuongXaAndQuanHuyenAndTinhThanh(
                        thongTinKhachHang.getDuong(),
                        thongTinKhachHang.getPhuongXa(),
                        thongTinKhachHang.getQuanHuyen(),
                        thongTinKhachHang.getTinhThanh()
                )
                .orElseGet(() -> {
                    DiaChi dc = new DiaChi();
                    dc.setDuong(thongTinKhachHang.getDuong());
                    dc.setPhuongXa(thongTinKhachHang.getPhuongXa());
                    dc.setQuanHuyen(thongTinKhachHang.getQuanHuyen());
                    dc.setTinhThanh(thongTinKhachHang.getTinhThanh());
                    dc.setQuocGia("Việt Nam");
                    dc.setLoaiDiaChi("GIAO_HANG");
                    dc.setLaMacDinh(false);
                    dc.setNgayTao(java.time.OffsetDateTime.now());
                    dc.setNgayCapNhat(java.time.OffsetDateTime.now());
                    return diaChiRepository.save(dc);
                });

        // Bước 2: Tạo hóa đơn và lưu trước để lấy ID
        HoaDon hoaDon = new HoaDon();
        hoaDon.setNgayTao(Instant.now());
        hoaDon.setNgayCapNhat(Instant.now());
        hoaDon.setNguoiTao("KHACH_VANG_LAI");
        hoaDon.setNguoiCapNhat("KHACH_VANG_LAI");
        hoaDon.setMaHoaDon(UUID.randomUUID().toString());
        hoaDon.setLoaiHoaDon(LoaiHoaDon.ONLINE);
        hoaDon.setTrangThaiDonHang(TrangThaiDonHang.CHO_XAC_NHAN);
        hoaDon.setTrangThaiThanhToan(
                "TIEN_MAT".equalsIgnoreCase(phuongThucThanhToan)
                        ? TrangThaiThanhToan.CHO_THANH_TOAN
                        : TrangThaiThanhToan.DA_THANH_TOAN
        );

        hoaDon.setNguoiNhanTen(thongTinKhachHang.getHoTen());
        hoaDon.setNguoiNhanSdt(thongTinKhachHang.getSoDienThoai());
        hoaDon.setDiaChiGiaoHang(diaChi);
        hoaDon.setPhiVanChuyen(new BigDecimal("30000")); // mặc định

        // Gán tạm giá trị để tránh lỗi null
        hoaDon.setTongTienHang(BigDecimal.ZERO);
        hoaDon.setGiaTriGiamGiaVoucher(BigDecimal.ZERO);
        hoaDon.setTongThanhToan(BigDecimal.ZERO);

        // Lưu để lấy ID
        hoaDon = hoaDonRepo.save(hoaDon);

        // Bước 3: Duyệt giỏ hàng và tạo hóa đơn chi tiết
        List<GioHangChiTiet> chiTiets = gioHangChiTietRepository.findByGioHangId(gioHangId);
        BigDecimal tongTienHang = BigDecimal.ZERO;

        for (GioHangChiTiet chiTiet : chiTiets) {
            int soLuong = chiTiet.getSoLuong();
            Long spctId = chiTiet.getSanPhamChiTiet().getId();

            // Lấy serial hợp lệ
            List<SerialNumber> serials = serialRepo.findTopNBySanPhamChiTietIdAndTrangThai(
                    spctId, TrangThaiSerialNumber.AVAILABLE.name(), soLuong
            );
            if (serials.size() < soLuong) {
                throw new RuntimeException("Không đủ serial cho sản phẩm " + spctId);
            }

            // Tạo chi tiết hóa đơn
            HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
            hoaDonChiTiet.setHoaDon(hoaDon);
            hoaDonChiTiet.setSanPhamChiTiet(chiTiet.getSanPhamChiTiet());
            hoaDonChiTiet.setSoLuong(soLuong);
            hoaDonChiTiet.setGiaGoc(chiTiet.getGiaTaiThoiDiemThem());
            hoaDonChiTiet.setGiaBan(chiTiet.getGiaTaiThoiDiemThem());
            hoaDonChiTiet.setThanhTien(chiTiet.getGiaTaiThoiDiemThem().multiply(BigDecimal.valueOf(soLuong)));
            hoaDonChiTiet.setNgayTao(Instant.now());
            hoaDonChiTiet.setNgayCapNhat(Instant.now());
            hoaDonChiTiet.setNguoiTao("KHACH_VANG_LAI");
            hoaDonChiTiet.setNguoiCapNhat("KHACH_VANG_LAI");
            hoaDonChiTiet.setVersion(1L);

            chiTietRepo.save(hoaDonChiTiet);
            tongTienHang = tongTienHang.add(hoaDonChiTiet.getThanhTien());

            // Gán serial cho chi tiết hóa đơn
            for (SerialNumber sn : serials) {
                sn.setTrangThai(TrangThaiSerialNumber.SOLD);
                serialRepo.save(sn);

                SerialNumberHoaDonChiTiet lienKet = new SerialNumberHoaDonChiTiet();
                lienKet.setSerialNumber(sn);
                lienKet.setHoaDonChiTiet(hoaDonChiTiet);
                lienKet.setNgayTao(Instant.now());
                serialNumberHoaDonChiTietRepository.save(lienKet);
            }
        }

    /*
    // Bước 4: Tìm phiếu giảm giá tốt nhất nếu có
    Long nguoiDungId = null;
    if (thongTinKhachHang.getEmail() != null && !thongTinKhachHang.getEmail().isBlank()) {
        NguoiDung nguoiDung = nguoiDungRepo.findByEmail(thongTinKhachHang.getEmail()).orElse(null);
        if (nguoiDung != null) {
            nguoiDungId = nguoiDung.getId();
        }
    }

    PhieuGiamGia phieuGiam = timPhieuGiamTotNhat(tongTienHang, nguoiDungId);
    BigDecimal giamGia = BigDecimal.ZERO;

    if (phieuGiam != null) {
        if ("PHAN_TRAM".equals(phieuGiam.getLoaiGiamGia())) {
            giamGia = tongTienHang.multiply(phieuGiam.getGiaTriGiam()).divide(BigDecimal.valueOf(100));
        } else if ("SO_TIEN_CO_DINH".equals(phieuGiam.getLoaiGiamGia())) {
            giamGia = phieuGiam.getGiaTriGiam();
        }

        // Thêm vào bảng hoa_don_phieu_giam_gia
        HoaDonPhieuGiamGia hoaDonPhieuGiamGia = new HoaDonPhieuGiamGia();
        HoaDonPhieuGiamGiaId hoaDonPhieuGiamGiaId = new HoaDonPhieuGiamGiaId(hoaDon.getId(), phieuGiam.getId());
        hoaDonPhieuGiamGia.setId(hoaDonPhieuGiamGiaId);
        hoaDonPhieuGiamGia.setHoaDon(hoaDon);
        if (hoaDonPhieuGiamGia.getGiaTriDaGiam() == null) {
            hoaDonPhieuGiamGia.setGiaTriDaGiam(BigDecimal.ZERO);
        }
        hoaDonPhieuGiamGiaRepository.save(hoaDonPhieuGiamGia);  // Lưu vào bảng hoa_don_phieu_giam_gia

        hoaDon.setPhieuGiamGias(new ArrayList<>(List.of(phieuGiam)));
    } else {
        // Trường hợp không có phiếu giảm giá, bạn có thể set giaTriDaGiam = BigDecimal.ZERO nếu cần.
        hoaDon.setPhieuGiamGias(new ArrayList<>());
    }
    */

        // Bước 5: Cập nhật lại hóa đơn với tổng tiền thực tế
        hoaDon.setTongTienHang(tongTienHang);
        hoaDon.setGiaTriGiamGiaVoucher(BigDecimal.ZERO);
        hoaDon.setTongThanhToan(
                tongTienHang.add(hoaDon.getPhiVanChuyen())
        );
        hoaDonRepo.save(hoaDon);

        // Bước 6: Xóa chi tiết giỏ hàng đã xử lý
        gioHangChiTietRepository.deleteByGioHangId(gioHangId);

        return hoaDon;
    }
    public PhieuGiamGia timPhieuGiamTotNhat(BigDecimal tongTien, Long nguoiDungId) {
        List<PhieuGiamGia> ds;

        if (nguoiDungId != null) {
            ds = phieuGiamGiaRepository.findPhieuCoTheDung(tongTien, nguoiDungId);
        } else {
            ds = phieuGiamGiaRepository.findPhieuCongKhaiCoTheDung(tongTien);
        }

        return ds.stream()
                .max(Comparator.comparing(p -> {
                    if ("PHAN_TRAM".equals(p.getLoaiGiamGia())) {
                        return tongTien.multiply(p.getGiaTriGiam()).divide(BigDecimal.valueOf(100));
                    } else {
                        return p.getGiaTriGiam();
                    }
                }))
                .orElse(null);
    }
    public HoaDon thanhToanMuaNgay(MuaNgayDTO dto) {
        ThanhToanOnlineDTO thongTin = dto.getThongTinKhachHang();

        // Xử lý địa chỉ như cũ
        DiaChi diaChi = diaChiRepository
                .findFirstByDuongAndPhuongXaAndQuanHuyenAndTinhThanh(
                        thongTin.getDuong(),
                        thongTin.getPhuongXa(),
                        thongTin.getQuanHuyen(),
                        thongTin.getTinhThanh()
                )
                .orElseGet(() -> {
                    DiaChi dc = new DiaChi();
                    dc.setDuong(thongTin.getDuong());
                    dc.setPhuongXa(thongTin.getPhuongXa());
                    dc.setQuanHuyen(thongTin.getQuanHuyen());
                    dc.setTinhThanh(thongTin.getTinhThanh());
                    dc.setQuocGia("Việt Nam");
                    dc.setLoaiDiaChi("GIAO_HANG");
                    dc.setLaMacDinh(false);
                    dc.setNgayTao(java.time.OffsetDateTime.now());
                    dc.setNgayCapNhat(java.time.OffsetDateTime.now());
                    return diaChiRepository.save(dc);
                });

        // Tạo hóa đơn
        HoaDon hoaDon = new HoaDon();
        hoaDon.setNgayTao(Instant.now());
        hoaDon.setNgayCapNhat(Instant.now());
        hoaDon.setNguoiTao("KHACH_VANG_LAI");
        hoaDon.setNguoiCapNhat("KHACH_VANG_LAI");
        String maHoaDon = "HD" + Instant.now().toEpochMilli();
        hoaDon.setMaHoaDon(maHoaDon);
        hoaDon.setLoaiHoaDon(LoaiHoaDon.ONLINE);
        hoaDon.setTrangThaiDonHang(TrangThaiDonHang.CHO_XAC_NHAN);
        hoaDon.setTrangThaiThanhToan(
                "TIEN_MAT".equalsIgnoreCase(dto.getPhuongThucThanhToan())
                        ? TrangThaiThanhToan.CHO_THANH_TOAN
                        : TrangThaiThanhToan.DA_THANH_TOAN
        );

        hoaDon.setNguoiNhanTen(thongTin.getHoTen());
        hoaDon.setNguoiNhanSdt(thongTin.getSoDienThoai());
        hoaDon.setDiaChiGiaoHang(diaChi);
        hoaDon.setPhiVanChuyen(new BigDecimal("30000"));
        hoaDon.setTongTienHang(BigDecimal.ZERO);
        hoaDon.setGiaTriGiamGiaVoucher(BigDecimal.ZERO);
        hoaDon.setTongThanhToan(BigDecimal.ZERO);

        hoaDon = hoaDonRepo.save(hoaDon);

        // Xử lý sản phẩm
        SanPhamChiTiet spct = sanPhamChiTietRepository.findById(dto.getSanPhamChiTietId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm chi tiết với ID: " + dto.getSanPhamChiTietId()));

        int soLuong = dto.getSoLuong();

        List<SerialNumber> serials = serialRepo.findTopNBySanPhamChiTietIdAndTrangThai(
                spct.getId(), TrangThaiSerialNumber.AVAILABLE.name(), soLuong
        );
        if (serials.size() < soLuong) {
            throw new RuntimeException("Không đủ serial cho sản phẩm");
        }
        BigDecimal gia = dto.getGiaTaiThoiDiemThem();
        if (gia == null) {
            // Lấy giá từ SanPhamChiTiet thay thế
            gia = spct.getGiaBan(); // hoặc getGiaKhuyenMai() nếu bạn có khuyến mãi
            if (gia == null) {
                throw new RuntimeException("Không có giá cho sản phẩm chi tiết ID: " + spct.getId());
            }
        }

        HoaDonChiTiet chiTiet = new HoaDonChiTiet();
        chiTiet.setHoaDon(hoaDon);
        chiTiet.setSanPhamChiTiet(spct);
        chiTiet.setSoLuong(soLuong);
        chiTiet.setGiaGoc(gia);
        chiTiet.setGiaBan(gia);
        chiTiet.setThanhTien(gia.multiply(BigDecimal.valueOf(soLuong)));

        chiTiet.setNgayTao(Instant.now());
        chiTiet.setNgayCapNhat(Instant.now());
        chiTiet.setNguoiTao("KHACH_VANG_LAI");
        chiTiet.setNguoiCapNhat("KHACH_VANG_LAI");
        chiTiet.setVersion(1L);

        chiTietRepo.save(chiTiet);

        for (SerialNumber sn : serials) {
            sn.setTrangThai(TrangThaiSerialNumber.SOLD);
            serialRepo.save(sn);

            SerialNumberHoaDonChiTiet lienKet = new SerialNumberHoaDonChiTiet();
            lienKet.setSerialNumber(sn);
            lienKet.setHoaDonChiTiet(chiTiet);
            lienKet.setNgayTao(Instant.now());
            serialNumberHoaDonChiTietRepository.save(lienKet);
        }

        BigDecimal tongTienHang = chiTiet.getThanhTien();

        hoaDon.setTongTienHang(tongTienHang);
        hoaDon.setTongThanhToan(tongTienHang.add(hoaDon.getPhiVanChuyen()));
        hoaDonRepo.save(hoaDon);

        return hoaDon;
    }
    @Transactional
    public void huyDonHang(String maHoaDon) {
        HoaDon hoaDon = hoaDonRepo.findByMaHoaDon(maHoaDon)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy hóa đơn: " + maHoaDon));

        if (!hoaDon.getTrangThaiDonHang().equals(TrangThaiDonHang.CHO_XAC_NHAN)) {
            throw new RuntimeException("Chỉ có thể hủy đơn hàng khi đang chờ xác nhận.");
        }

        // Cập nhật trạng thái hóa đơn
        hoaDon.setTrangThaiDonHang(TrangThaiDonHang.DA_HUY);
        hoaDon.setNgayCapNhat(Instant.now());
        hoaDonRepo.save(hoaDon);

        // Tìm các chi tiết hóa đơn
        List<HoaDonChiTiet> chiTiets = chiTietRepo.findByHoaDonId(hoaDon.getId());

        for (HoaDonChiTiet ct : chiTiets) {
            // Tìm các serial liên kết
            List<SerialNumberHoaDonChiTiet> lienKets = serialNumberHoaDonChiTietRepository
                    .findByHoaDonChiTietId(ct.getId());

            for (SerialNumberHoaDonChiTiet lk : lienKets) {
                SerialNumber sn = lk.getSerialNumber();
                sn.setTrangThai(TrangThaiSerialNumber.AVAILABLE);
                serialRepo.save(sn);
            }
        }
    }
}
