package com.example.lapxpertbe.Controller;

import com.example.lapxpertbe.Service.GioHangService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/gio-hang")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class GioHangController {
    private final GioHangService gioHangService;
    @PostMapping("/them")
    public ResponseEntity<?> themVaoGio(@RequestParam Long gioHangId,
                                        @RequestParam Long sanPhamChiTietId) {
        try {
            gioHangService.themVaoGio(sanPhamChiTietId, gioHangId);
            return ResponseEntity.ok("Đã thêm sản phẩm vào giỏ hàng.");
        } catch (RuntimeException e) {
            e.printStackTrace(); // ✅ thêm dòng này
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 📦 Xem giỏ hàng
    @GetMapping("/{gioHangId}")
    public ResponseEntity<?> xemGioHang(@PathVariable Long gioHangId) {
        return ResponseEntity.ok(gioHangService.layDanhSachSanPhamTrongGio(gioHangId));
    }

    // ♻️ Cập nhật số lượng
    @PutMapping("/cap-nhat-so-luong")
    public ResponseEntity<?> capNhatSoLuong(@RequestParam Long gioHangId,
                                            @RequestParam Long sanPhamChiTietId,
                                            @RequestParam int soLuongMoi) {
        try {
            gioHangService.capNhatSoLuong(sanPhamChiTietId, gioHangId, soLuongMoi);
            return ResponseEntity.ok("Cập nhật số lượng thành công.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // ❌ Xóa sản phẩm khỏi giỏ
    @DeleteMapping("/xoa")
    public ResponseEntity<?> xoaSanPhamKhoiGio(@RequestParam Long gioHangId,
                                               @RequestParam Long sanPhamChiTietId) {
        gioHangService.xoaSanPhamKhoiGio(gioHangId, sanPhamChiTietId);
        return ResponseEntity.ok("Đã xóa sản phẩm khỏi giỏ hàng.");
    }
    @DeleteMapping("/xoa-het")
    public ResponseEntity<?> xoaHet(@RequestParam Long gioHangId) {
        gioHangService.xoaToanBoGioHang(gioHangId);
        return ResponseEntity.ok("Đã xóa toàn bộ giỏ hàng.");
    }

}
