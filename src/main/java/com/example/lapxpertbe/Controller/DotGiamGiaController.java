package com.example.lapxpertbe.Controller;

import com.example.lapxpertbe.Enity.DotGiamGia;
import com.example.lapxpertbe.Enity.SanPhamChiTietDotGiamGia;
import com.example.lapxpertbe.Repository.DotGiamGiaRepository;
// import com.example.lapxpertbe.Service.SanPhamChiTietDotGiamGiaService; // Có thể bỏ comment nếu không dùng tạm thời
import com.example.lapxpertbe.Service.SanPhamChiTietDotGiamGiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/dot-giam-gia")
@CrossOrigin("*")
public class DotGiamGiaController {

    @Autowired
    private SanPhamChiTietDotGiamGiaService sanPhamChiTietDotGiamGiaService;

    @Autowired
    private DotGiamGiaRepository dotGiamGiaRepo;

    // @GetMapping("/{id}/san-pham")
    // public List<SanPhamChiTietDotGiamGia> getSanPhamTheoDot(@PathVariable("id") Long dotGiamGiaId) {
    //     return sanPhamChiTietDotGiamGiaService.getByDotGiamGia(dotGiamGiaId);
    // }

    @GetMapping("/test-json") // TẠO API TEST MỚI
    public List<DotGiamGia> testJson() {
        // Trả về một list đơn giản, không liên quan đến DB hay các mối quan hệ phức tạp
        List<DotGiamGia> testList = new ArrayList<>();
        DotGiamGia dgg = new DotGiamGia();
        dgg.setId(99L);
        dgg.setTenDotGiamGia("Dot Test");
        // Đảm bảo các trường khác có giá trị mặc định để không null
        dgg.setMaDotGiamGia("TEST01");
        // ... set các trường khác nếu cần
        testList.add(dgg);
        return testList;
    }

    @GetMapping("/all")
    public List<DotGiamGia> getAll() {
        return dotGiamGiaRepo.findAll();
    }


    // @GetMapping
    // public List<DotGiamGia> getAllDotGiamGia() {
    //     return dotGiamGiaRepo.findAll();
    // }


    // @GetMapping("/dot/{id}")
    // public ResponseEntity<?> getDot(@PathVariable Long id) {
    //     DotGiamGia dot = dotGiamGiaRepo.findById(id).orElse(null);
    //     if (dot == null) return ResponseEntity.notFound().build();
    //
    //     boolean trangThai = dot.isTrangThai(); // Gọi hàm boolean đã custom
    //     return ResponseEntity.ok("Trạng thái: " + (trangThai ? "Đã diễn ra" : "Chưa diễn ra"));
    // }
}