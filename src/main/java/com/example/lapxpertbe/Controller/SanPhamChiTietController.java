package com.example.lapxpertbe.Controller;

import com.example.lapxpertbe.DTO.ChiTietSanPhamDto;
import com.example.lapxpertbe.Enity.SanPham;
import com.example.lapxpertbe.Service.ChiTietSanPhamService;
import com.example.lapxpertbe.Service.SanPhamOnlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/chi-tiet-san-pham")
@CrossOrigin(origins = "*") //
public class SanPhamChiTietController {
    @Autowired
    private ChiTietSanPhamService chiTietSanPhamService;
    @Autowired
    private SanPhamOnlineService sanPhamOnlineService;


    @GetMapping("by-san-pham/{id}")
    public List<ChiTietSanPhamDto> getChiTietBySanPhamId(@PathVariable("id") Long sanPhamId) {
        return chiTietSanPhamService.getBySanPhamId(sanPhamId);
    }
    @GetMapping("{id}")
    public ResponseEntity<SanPham> getSanPhamById(@PathVariable Long id) {
        Optional<SanPham> sp = sanPhamOnlineService.findById(id);
        return sp.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("by-danh-muc/{danhMucId}")
    public ResponseEntity<List<ChiTietSanPhamDto>> getChiTietByDanhMucId(@PathVariable("danhMucId") Long danhMucId) {
        List<ChiTietSanPhamDto> chiTietSanPhams = chiTietSanPhamService.getByDanhMucId(danhMucId);
        if (chiTietSanPhams.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(chiTietSanPhams);
    }
    @GetMapping
    public ResponseEntity<List<ChiTietSanPhamDto>> getAllChiTietSanPhams() {
        List<ChiTietSanPhamDto> chiTietSanPhams = chiTietSanPhamService.getAllChiTietSanPhams();
        if (chiTietSanPhams.isEmpty()) {
            return ResponseEntity.noContent().build(); // Trả về 204 nếu không có dữ liệu
        }
        return ResponseEntity.ok(chiTietSanPhams);
    }
}
