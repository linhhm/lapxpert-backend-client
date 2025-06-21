package com.example.lapxpertbe.Controller;

import com.example.lapxpertbe.DTO.ThanhToanOnlineDTO;
import com.example.lapxpertbe.Enity.HoaDon;
import com.example.lapxpertbe.Service.ThanhToanService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1thanh-toan")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ThanhToanController {
    private final ThanhToanService thanhToanService;

    @PostMapping("/online")
    public ResponseEntity<?> thanhToanOnline(
            @RequestParam Long gioHangId,
            @RequestParam String phuongThucThanhToan,
            @RequestBody ThanhToanOnlineDTO thongTinKhachHang
    ) {
        HoaDon hoaDon = thanhToanService.thanhToanOnline(gioHangId, thongTinKhachHang, phuongThucThanhToan);
        return ResponseEntity.ok(hoaDon);
    }
}

@Data
class ThanhToanOnlineRequest {
    private List<Long> serialIds;
    private ThanhToanOnlineDTO thongTinKhachHang; // class chứa info họ tên, email, sđt...
    private String phuongThucThanhToan; // ví dụ: "TIEN_MAT" hoặc "THANH_TOAN_ONLINE"
}
