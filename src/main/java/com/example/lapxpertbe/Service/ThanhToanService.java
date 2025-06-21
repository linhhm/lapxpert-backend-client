package com.example.lapxpertbe.Service;

import com.example.lapxpertbe.DTO.ThanhToanOnlineDTO;
import com.example.lapxpertbe.Enity.*;
import com.example.lapxpertbe.Repository.*;
import com.example.lapxpertbe.enums.TrangThaiSerialNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ThanhToanService {
    private final SerialNumberRepository serialRepo;
    private final HoaDonRepository hoaDonRepo;
    private final HoaDonChiTietRepository chiTietRepo;
    private final GioHangChiTietRepository gioHangChiTietRepository;
    private final NguoiDungRepository nguoiDungRepo;
    public HoaDon thanhToanOnline(Long gioHangId, ThanhToanOnlineDTO thongTinKhachHang, String phuongThucThanhToan) {
        HoaDon hoaDon = new HoaDon();
        hoaDon.setNgayTao(Instant.now());
        hoaDon.setNgayCapNhat(Instant.now());
        hoaDon.setNguoiTao("KHACH_VANG_LAI");
        hoaDon.setNguoiCapNhat("KHACH_VANG_LAI");
        hoaDon.setMaHoaDon(UUID.randomUUID().toString());
        hoaDon.setLoaiHoaDon("ONL");
        hoaDon.setTrangThaiDonHang("CHO_XAC_NHAN");
        hoaDon.setTrangThaiThanhToan(
                phuongThucThanhToan.equalsIgnoreCase("TIEN_MAT") ? "CHO_THANH_TOAN" : "DA_THANH_TOAN"
        );
        hoaDon.setNguoiNhanTen(thongTinKhachHang.getHoTen());
        hoaDon.setNguoiNhanSdt(thongTinKhachHang.getSoDienThoai());

        List<GioHangChiTiet> chiTiets = gioHangChiTietRepository.findByGioHangId(gioHangId);

        BigDecimal tongTienHang = BigDecimal.ZERO;

        for (GioHangChiTiet chiTiet : chiTiets) {
            int soLuong = chiTiet.getSoLuong();
            Long spctId = chiTiet.getSanPhamChiTiet().getId();

            List<SerialNumber> serials = serialRepo.findTopNBySanPhamChiTietIdAndTrangThai(
                    spctId,
                    TrangThaiSerialNumber.AVAILABLE.name(),  // chuyển enum thành String
                    soLuong
            );
            if (serials.size() < soLuong) {
                throw new RuntimeException("Không đủ serial cho sản phẩm " + spctId);
            }

            HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
            hoaDonChiTiet.setHoaDon(hoaDon);
            hoaDonChiTiet.setSanPhamChiTiet(chiTiet.getSanPhamChiTiet());
            hoaDonChiTiet.setSoLuong(soLuong);
            hoaDonChiTiet.setGiaGoc(chiTiet.getGiaTaiThoiDiemThem());
            hoaDonChiTiet.setGiaBan(chiTiet.getGiaTaiThoiDiemThem());
            hoaDonChiTiet.setThanhTien(chiTiet.getGiaTaiThoiDiemThem().multiply(BigDecimal.valueOf(soLuong)));
            chiTietRepo.save(hoaDonChiTiet);

            tongTienHang = tongTienHang.add(hoaDonChiTiet.getThanhTien());

            for (SerialNumber sn : serials) {
                sn.setTrangThai(TrangThaiSerialNumber.SOLD);
                serialRepo.save(sn);
            }
        }

        hoaDon.setTongTienHang(tongTienHang);
        hoaDon.setGiaTriGiamGiaVoucher(BigDecimal.ZERO);
        hoaDon.setPhiVanChuyen(new BigDecimal("30000"));
        hoaDon.setTongThanhToan(tongTienHang.add(hoaDon.getPhiVanChuyen()));

        hoaDonRepo.save(hoaDon);

        gioHangChiTietRepository.deleteByGioHangId(gioHangId);

        return hoaDon;
    }
}
