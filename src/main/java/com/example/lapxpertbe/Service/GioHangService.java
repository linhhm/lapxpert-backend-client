package com.example.lapxpertbe.Service;

import com.example.lapxpertbe.DTO.GioHangIdDTO;
import com.example.lapxpertbe.Enity.GioHang;
import com.example.lapxpertbe.Enity.GioHangChiTiet;
import com.example.lapxpertbe.Enity.NguoiDung;
import com.example.lapxpertbe.Repository.*;
import com.example.lapxpertbe.enums.TrangThaiSerialNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GioHangService {
    private final SerialNumberRepository serialRepo;
    private final GioHangRepository gioHangRepo;
    private final GioHangChiTietRepository chiTietRepo;
    private final SanPhamChiTietRepository sanPhamChiTietRepository;
    private final NguoiDungRepository nguoiDungRepository;

    // 🔄 Lấy hoặc tạo mới giỏ hàng từ sessionId hoặc nguoiDungId
    public GioHang getOrCreateGioHang(String sessionId, Long nguoiDungId) {
        if (nguoiDungId != null) {
            return gioHangRepo.findByNguoiDungId(nguoiDungId)
                    .orElseGet(() -> {
                        GioHang gh = new GioHang();
                        gh.setNguoiDung(nguoiDungRepository.findById(nguoiDungId).orElseThrow());
                        gh.setNgayTao(Instant.now());
                        gh.setNgayCapNhat(Instant.now());
                        return gioHangRepo.save(gh);
                    });
        }

        if (sessionId != null && !sessionId.isEmpty()) {
            return gioHangRepo.findBySessionId(sessionId)
                    .orElseGet(() -> {
                        GioHang gh = new GioHang();
                        gh.setSessionId(sessionId);
                        gh.setNgayTao(Instant.now());
                        gh.setNgayCapNhat(Instant.now());
                        return gioHangRepo.save(gh);
                    });
        }

        throw new RuntimeException("Thiếu sessionId hoặc nguoiDungId");
    }

    // ➕ Thêm sản phẩm vào giỏ
    public void themVaoGio(Long sanPhamChiTietId, String sessionId, Long nguoiDungId) {
        GioHang gioHang = getOrCreateGioHang(sessionId, nguoiDungId);

        long soSerialAvailable = serialRepo.countBySanPhamChiTietIdAndTrangThai(
                sanPhamChiTietId, TrangThaiSerialNumber.AVAILABLE
        );

        GioHangChiTiet gioHangChiTiet = chiTietRepo.findByGioHangIdAndSanPhamChiTietId(
                gioHang.getId(), sanPhamChiTietId
        ).orElse(null);

        int soLuongHienTai = gioHangChiTiet != null ? gioHangChiTiet.getSoLuong() : 0;

        if (soLuongHienTai + 1 > soSerialAvailable) {
            throw new RuntimeException("Không đủ số lượng sản phẩm, chỉ còn " + soSerialAvailable);
        }

        if (gioHangChiTiet == null) {
            gioHangChiTiet = new GioHangChiTiet();
            gioHangChiTiet.setGioHang(gioHang);
            var spct = sanPhamChiTietRepository.findById(sanPhamChiTietId)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm chi tiết"));

            gioHangChiTiet.setSanPhamChiTiet(spct);
            gioHangChiTiet.setGiaTaiThoiDiemThem(
                    spct.getGiaKhuyenMai() != null ? spct.getGiaKhuyenMai() : spct.getGiaBan()
            );
            gioHangChiTiet.setSoLuong(1);
            gioHangChiTiet.setNgayTao(Instant.now());
        } else {
            gioHangChiTiet.setSoLuong(soLuongHienTai + 1);
        }

        gioHangChiTiet.setNgayCapNhat(Instant.now());
        gioHang.setNgayCapNhat(Instant.now());
        gioHangRepo.save(gioHang); // cập nhật timestamp
        chiTietRepo.save(gioHangChiTiet);
    }

    // 🔁 Cập nhật số lượng
    public void capNhatSoLuong(Long sanPhamChiTietId, int soLuongMoi, String sessionId, Long nguoiDungId) {
        if (soLuongMoi < 0) throw new RuntimeException("Số lượng không hợp lệ");

        GioHang gioHang = getOrCreateGioHang(sessionId, nguoiDungId);

        long soSerialAvailable = serialRepo.countBySanPhamChiTietIdAndTrangThai(
                sanPhamChiTietId, TrangThaiSerialNumber.AVAILABLE
        );

        if (soLuongMoi > soSerialAvailable) {
            throw new RuntimeException("Không đủ số lượng sản phẩm");
        }

        GioHangChiTiet gioHangChiTiet = chiTietRepo.findByGioHangIdAndSanPhamChiTietId(
                gioHang.getId(), sanPhamChiTietId
        ).orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm trong giỏ"));

        if (soLuongMoi == 0) {
            chiTietRepo.delete(gioHangChiTiet);
        } else {
            gioHangChiTiet.setSoLuong(soLuongMoi);
            gioHangChiTiet.setNgayCapNhat(Instant.now());
            chiTietRepo.save(gioHangChiTiet);
        }

        gioHang.setNgayCapNhat(Instant.now());
        gioHangRepo.save(gioHang);
    }

    // ❌ Xóa sản phẩm
    public void xoaSanPhamKhoiGio(Long sanPhamChiTietId, String sessionId, Long nguoiDungId) {
        GioHang gioHang = getOrCreateGioHang(sessionId, nguoiDungId);
        GioHangChiTiet chiTiet = chiTietRepo.findByGioHangIdAndSanPhamChiTietId(
                gioHang.getId(), sanPhamChiTietId
        ).orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm trong giỏ"));
        chiTietRepo.delete(chiTiet);
        gioHang.setNgayCapNhat(Instant.now());
        gioHangRepo.save(gioHang);
    }
    public List<GioHangIdDTO> layDanhSachSanPhamTrongGio(String sessionId, Long nguoiDungId) {
        GioHang gioHang = getOrCreateGioHang(sessionId, nguoiDungId);
        List<GioHangChiTiet> list = chiTietRepo.findByGioHangId(gioHang.getId());

        return list.stream().map(ct -> {
            List<String> seri = serialRepo
                    .findBySanPhamChiTietIdAndTrangThai(
                            ct.getSanPhamChiTiet().getId(),
                            TrangThaiSerialNumber.AVAILABLE
                    ).stream()
                    .limit(ct.getSoLuong()) // 🔄 chỉ lấy số lượng tương ứng
                    .map(sn -> sn.getSerialNumberValue())
                    .toList();

            return new GioHangIdDTO(
                    gioHang.getId(),
                    ct.getSanPhamChiTiet(),
                    ct.getSoLuong(),
                    ct.getGiaTaiThoiDiemThem(),
                    seri
            );
        }).collect(Collectors.toList());
    }
    // ❌ Xóa hết giỏ hàng
    public void xoaToanBoGioHang(String sessionId, Long nguoiDungId) {
        GioHang gioHang = getOrCreateGioHang(sessionId, nguoiDungId);
        chiTietRepo.deleteByGioHangId(gioHang.getId());
        gioHang.setNgayCapNhat(Instant.now());
        gioHangRepo.save(gioHang);
    }
    public int demTongSoLuongSanPhamTrongGio(Long gioHangId) {
        return chiTietRepo.findByGioHangId(gioHangId).stream()
                .mapToInt(GioHangChiTiet::getSoLuong)
                .sum();
    }
}
