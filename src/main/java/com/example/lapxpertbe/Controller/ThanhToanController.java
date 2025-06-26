package com.example.lapxpertbe.Controller;

import com.example.lapxpertbe.DTO.MuaNgayDTO;
import com.example.lapxpertbe.DTO.SerialInfoDTO;
import com.example.lapxpertbe.DTO.ThanhToanOnlineDTO;
import com.example.lapxpertbe.Enity.HoaDon;
import com.example.lapxpertbe.Service.ThanhToanService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/thanh-toan")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ThanhToanController {
    private final ThanhToanService thanhToanService;

    @PostMapping("/online")
    public ResponseEntity<?> thanhToanOnline(@RequestParam Long gioHangId, @RequestBody ThanhToanOnlineRequest request){
    // Gọi service
        HoaDon hoaDon = thanhToanService.thanhToanOnline(
                gioHangId,
                request.getThongTinKhachHang(),
                request.getPhuongThucThanhToan()
                // bạn có thể truyền thêm list serial nếu cần
        );

        return ResponseEntity.ok(hoaDon);
    }
    @GetMapping("/serials")
    public ResponseEntity<List<SerialInfoDTO>> getSerialsTamThoi(
            @RequestParam Long sanPhamChiTietId,
            @RequestParam int soLuong
    ) {
        return ResponseEntity.ok(thanhToanService.laySerialNumberTamThoi(sanPhamChiTietId, soLuong));
    }
    @PostMapping("/mua-ngay")
    public ResponseEntity<HoaDon> thanhToanMuaNgay(@RequestBody MuaNgayDTO dto) {
        HoaDon hoaDon = thanhToanService.thanhToanMuaNgay(dto);
        return ResponseEntity.ok(hoaDon);
    }
    @Data
    public static class ThanhToanOnlineRequest {
        private List<Long> serialIds;
        private ThanhToanOnlineDTO thongTinKhachHang;
        private String phuongThucThanhToan;
    }
}
