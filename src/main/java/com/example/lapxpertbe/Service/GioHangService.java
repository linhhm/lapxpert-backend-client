package com.example.lapxpertbe.Service;

import com.example.lapxpertbe.Enity.GioHang;
import com.example.lapxpertbe.Enity.GioHangChiTiet;
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
    private final HoaDonChiTietRepository hoaDonChiTietRepository;
    private final GioHangChiTietRepository chiTietRepo;
    private final SanPhamChiTietRepository sanPhamChiTietRepository;

    public void themVaoGio(Long sanPhamChiTietId, Long gioHangId) {
        long soSerialAvailable = serialRepo.countBySanPhamChiTietIdAndTrangThai(
                sanPhamChiTietId, TrangThaiSerialNumber.AVAILABLE
        );

        // ✅ Lấy hoặc tạo mới giỏ hàng nếu chưa tồn tại
        GioHang gioHang = gioHangRepo.findById(gioHangId)
                .orElseGet(() -> {
                    GioHang newGio = new GioHang();
                    newGio.setId(gioHangId);
                    newGio.setNgayCapNhat(Instant.now());
                    newGio.setNgayTao(Instant.now());
                    return gioHangRepo.save(newGio);
                });

        // ✅ Lấy chi tiết SP trong giỏ (nếu có)
        GioHangChiTiet gioHangChiTiet = chiTietRepo.findByGioHangIdAndSanPhamChiTietId(
                gioHangId, sanPhamChiTietId
        ).orElse(null);

        int soLuongHienTai = gioHangChiTiet != null ? gioHangChiTiet.getSoLuong() : 0;

        if (soLuongHienTai + 1 > soSerialAvailable) {
            throw new RuntimeException("Sản phẩm đã hết hàng hoặc không đủ số lượng");
        }

        if (gioHangChiTiet == null) {
            gioHangChiTiet = new GioHangChiTiet();
            gioHangChiTiet.setGioHang(gioHang);

            var spct = sanPhamChiTietRepository.findById(sanPhamChiTietId)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm chi tiết"));

            gioHangChiTiet.setSanPhamChiTiet(spct);
            gioHangChiTiet.setGiaTaiThoiDiemThem(spct.getGiaKhuyenMai() != null ? spct.getGiaKhuyenMai() : spct.getGiaBan());
            gioHangChiTiet.setSoLuong(1);

            // ✅ Thêm dòng này:
            gioHangChiTiet.setNgayTao(Instant.now());
            gioHangChiTiet.setNgayCapNhat(Instant.now());
        }

        chiTietRepo.save(gioHangChiTiet);
    }


    public void capNhatSoLuong(Long sanPhamChiTietId, Long gioHangId, int soLuongMoi) {
        if (soLuongMoi < 0) throw new RuntimeException("Số lượng không hợp lệ");

        long soSerialAvailable = serialRepo.countBySanPhamChiTietIdAndTrangThai(
                sanPhamChiTietId, TrangThaiSerialNumber.AVAILABLE
        );

        if (soLuongMoi > soSerialAvailable) {
            throw new RuntimeException("Không đủ hàng để cập nhật số lượng");
        }

        GioHangChiTiet gioHangChiTiet = chiTietRepo.findByGioHangIdAndSanPhamChiTietId(
                gioHangId, sanPhamChiTietId
        ).orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm trong giỏ"));

        if (soLuongMoi == 0) {
            chiTietRepo.delete(gioHangChiTiet);
        } else {
            gioHangChiTiet.setSoLuong(soLuongMoi);
            chiTietRepo.save(gioHangChiTiet);
        }
    }
    public void xoaSanPhamKhoiGio(Long gioHangId, Long sanPhamChiTietId) {
        var chiTiet = chiTietRepo.findByGioHangIdAndSanPhamChiTietId(gioHangId, sanPhamChiTietId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm trong giỏ"));
        chiTietRepo.delete(chiTiet); // dùng phương thức delete thông thường
    }
    public List<GioHangChiTiet> layDanhSachSanPhamTrongGio(Long gioHangId) {
        return chiTietRepo.findByGioHangId(gioHangId);
    }

    public void xoaToanBoGioHang(Long gioHangId) {
        System.out.println(">>> Xóa sản phẩm: gioHangId = " + gioHangId );
        chiTietRepo.deleteByGioHangId(gioHangId);
    }

    public GioHang taoGioHangMoi() {
        GioHang gioHang = new GioHang();
        return gioHangRepo.save(gioHang);
    }

    public int demTongSoLuongSanPhamTrongGio(Long gioHangId) {
        return chiTietRepo.findByGioHangId(gioHangId).stream()
                .mapToInt(GioHangChiTiet::getSoLuong)
                .sum();
    }
}
