package com.example.lapxpertbe.Controller;

import com.example.lapxpertbe.Service.GioHangService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/gio-hang")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class GioHangController {
    private final GioHangService gioHangService;

    // ➕ Thêm sản phẩm vào giỏ hàng
    @PostMapping("/them")
    public ResponseEntity<?> themVaoGio(@RequestParam Long sanPhamChiTietId,
                                        @RequestParam(required = false) String sessionId,
                                        @RequestParam(required = false) Long nguoiDungId) {
        try {
            gioHangService.themVaoGio(sanPhamChiTietId, sessionId, nguoiDungId);
            return ResponseEntity.ok("Đã thêm sản phẩm vào giỏ hàng.");
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Lỗi khi thêm sản phẩm: " + e.getMessage());
        }
    }

    // 📦 Xem giỏ hàng hiện tại
    @GetMapping("/xem")
    public ResponseEntity<?> xemGioHang(@RequestParam(required = false) String sessionId,
                                        @RequestParam(required = false) Long nguoiDungId) {
        try {
            return ResponseEntity.ok(gioHangService.layDanhSachSanPhamTrongGio(sessionId, nguoiDungId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Không thể xem giỏ hàng: " + e.getMessage());
        }
    }

    // ♻️ Cập nhật số lượng sản phẩm
    @PutMapping("/cap-nhat-so-luong")
    public ResponseEntity<?> capNhatSoLuong(@RequestParam Long sanPhamChiTietId,
                                            @RequestParam int soLuongMoi,
                                            @RequestParam(required = false) String sessionId,
                                            @RequestParam(required = false) Long nguoiDungId) {
        try {
            gioHangService.capNhatSoLuong(sanPhamChiTietId, soLuongMoi, sessionId, nguoiDungId);
            return ResponseEntity.ok("Đã cập nhật số lượng.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Lỗi khi cập nhật: " + e.getMessage());
        }
    }
    // ❌ Xóa một sản phẩm khỏi giỏ
    @DeleteMapping("/xoa")
    public ResponseEntity<?> xoaSanPhamKhoiGio(@RequestParam Long sanPhamChiTietId,
                                               @RequestParam(required = false) String sessionId,
                                               @RequestParam(required = false) Long nguoiDungId) {
        try {
            gioHangService.xoaSanPhamKhoiGio(sanPhamChiTietId, sessionId, nguoiDungId);
            return ResponseEntity.ok("Đã xóa sản phẩm khỏi giỏ.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Lỗi khi xóa sản phẩm: " + e.getMessage());
        }
    }

    // ❌ Xóa toàn bộ giỏ hàng
    @DeleteMapping("/xoa-het")
    public ResponseEntity<?> xoaHet(@RequestParam(required = false) String sessionId,
                                    @RequestParam(required = false) Long nguoiDungId) {
        try {
            gioHangService.xoaToanBoGioHang(sessionId, nguoiDungId);
            return ResponseEntity.ok("Đã xóa toàn bộ giỏ hàng.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Lỗi khi xóa toàn bộ giỏ hàng: " + e.getMessage());
        }
    }
    @GetMapping("/{gioHangId}/tong-so-luong")
    public int demTongSoLuong(@PathVariable Long gioHangId) {
        return gioHangService.demTongSoLuongSanPhamTrongGio(gioHangId);
    }
}
