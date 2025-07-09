package com.example.lapxpertbe.Controller;

import com.example.lapxpertbe.DTO.SanPhamDTO;
import com.example.lapxpertbe.Enity.SanPham;
import com.example.lapxpertbe.Service.SanPhamOnlineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/san-pham-online")

public class SanPhamOnlineController {

    private final SanPhamOnlineService sanPhamOnlineService;


    @GetMapping
    public List<SanPham> hienThiSanPham() {
        return sanPhamOnlineService.getSanPhamActive();
    }

//    @GetMapping("/san-pham/loc")
//    public List<SanPhamDTO> locSanPham(
//            @RequestParam Double minGia,
//            @RequestParam Double maxGia
//    ) {
//        return sanPhamOnlineService.locSanPhamTheoGia(minGia, maxGia);
//    }

    @GetMapping("khoang-gia")
    public List<SanPhamDTO> hienThiSanPham1() {
        return sanPhamOnlineService.getSanPhamKhoangGia();
    }
    // Đây là phương thức bạn cần có
    @GetMapping("/top-selling") // Thêm mapping cho endpoint này
    public ResponseEntity<List<SanPham>> getTopSellingProducts(@RequestParam(defaultValue = "8") int limit) {
        List<SanPham> topSellingProducts = sanPhamOnlineService.getTopSellingProducts(limit);
        return ResponseEntity.ok(topSellingProducts);
    }

    @GetMapping("/san-pham-active")
    public ResponseEntity<List<SanPham>> getSanPhamActive() {
        List<SanPham> sanPhamActive = sanPhamOnlineService.getSanPhamActive();
        return ResponseEntity.ok(sanPhamActive);
    }

    @GetMapping("/by-danh-muc/{id}")
    public ResponseEntity<List<SanPham>> getProductsByCategoryId(@PathVariable Long id) {
        try {
            List<SanPham> products = sanPhamOnlineService.getProductsByCategoryId(id);


            return ResponseEntity.ok(products);
        } catch (Exception e) {
            System.err.println("Lỗi khi lấy sản phẩm theo danh mục ID " + id + ": " + e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }
}



