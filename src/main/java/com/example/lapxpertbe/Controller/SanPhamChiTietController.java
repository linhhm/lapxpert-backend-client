package com.example.lapxpertbe.Controller;

import com.example.lapxpertbe.DTO.ChiTietSanPhamDto;
import com.example.lapxpertbe.Service.ChiTietSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/chi-tiet-san-pham")
@CrossOrigin(origins = "*") //
public class SanPhamChiTietController {
    @Autowired
    private ChiTietSanPhamService chiTietSanPhamService;

    @GetMapping
    public ResponseEntity<List<ChiTietSanPhamDto>> getAllChiTietSanPhams() {
        List<ChiTietSanPhamDto> chiTietSanPhams = chiTietSanPhamService.getAllChiTietSanPhams();
        if (chiTietSanPhams.isEmpty()) {
            return ResponseEntity.noContent().build(); // Trả về 204 nếu không có dữ liệu
        }
        return ResponseEntity.ok(chiTietSanPhams);
    }


    @GetMapping("by-san-pham/{id}")
    public ResponseEntity<List<ChiTietSanPhamDto>> getChiTietBySanPhamId(@PathVariable("id") Long sanPhamId) {
        List<ChiTietSanPhamDto> chiTietSanPhams = chiTietSanPhamService.getBySanPhamId(sanPhamId);
        if (chiTietSanPhams.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(chiTietSanPhams);
    }

    @GetMapping("by-danh-muc/{danhMucId}")
    public ResponseEntity<List<ChiTietSanPhamDto>> getChiTietByDanhMucId(@PathVariable("danhMucId") Long danhMucId) {
        List<ChiTietSanPhamDto> chiTietSanPhams = chiTietSanPhamService.getByDanhMucId(danhMucId);
        if (chiTietSanPhams.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(chiTietSanPhams);
    }


}
