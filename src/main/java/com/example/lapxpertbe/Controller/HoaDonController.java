package com.example.lapxpertbe.Controller;

import com.example.lapxpertbe.DTO.HoaDonDTO;
import com.example.lapxpertbe.Service.HoaDonService;
import com.example.lapxpertbe.Service.ThanhToanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/hoa-don")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class HoaDonController {
    private final HoaDonService hoaDonService;
    private final ThanhToanService thanhToanService;

    @GetMapping("/{maHoaDon}")
    public ResponseEntity<HoaDonDTO> getByMaHoaDon(@PathVariable String maHoaDon) {
        return ResponseEntity.ok(hoaDonService.getByMaHoaDon(maHoaDon));
    }
    @PutMapping("/huy/{maHoaDon}")
    public ResponseEntity<?> huyDon(@PathVariable String maHoaDon) {
        try {
            thanhToanService.huyDonHang(maHoaDon);
            return ResponseEntity.ok("Đơn hàng đã được hủy thành công");
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
