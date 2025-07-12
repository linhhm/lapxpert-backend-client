package com.example.lapxpertbe.Controller;

import com.example.lapxpertbe.Enity.PhieuGiamGia;
import com.example.lapxpertbe.Service.PhieuGiamGiaSerice;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/phieu-giam-gia")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PhieuGiamGiaController {
    @Autowired
    private PhieuGiamGiaSerice service;
//    @GetMapping("/by-code/{ma}")
//    public ResponseEntity<?> getVoucherByCode(@PathVariable("ma") String ma) {
//        return service.findByCodeValid(ma)
//                .map(voucher -> ResponseEntity.ok(voucher))  // Trả về PhieuGiamGia nếu tìm thấy
//                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
//                        .body("Mã giảm giá không tồn tại hoặc đã hết hạn")); // Trả về thông báo lỗi dưới dạng String nếu không tìm thấy
//    }
    @GetMapping("/all")
    public List<PhieuGiamGia> getAllVouchers() {
        return service.findAll();
    }

    // ✅ API này dùng để load lên trang chủ (công khai, đang còn hạn)
    @GetMapping("/hien-thi-trang-chu")
    public ResponseEntity<List<PhieuGiamGia>> getPhieuTrangChu() {
        return ResponseEntity.ok(service.getPhieuTrangChu());
    }

    // ⭐ API mới để lấy Top N Voucher ⭐
    @GetMapping("/top-discount")
    public ResponseEntity<List<PhieuGiamGia>> getTopDiscountVouchers(@RequestParam(defaultValue = "5") int limit) {
        List<PhieuGiamGia> topVouchers = service.getTopVouchersByDiscount(limit);
        if (topVouchers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(topVouchers);
    }
}
