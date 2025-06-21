package com.example.lapxpertbe.Controller;

import com.example.lapxpertbe.DTO.ChiTietSanPhamDto;
import com.example.lapxpertbe.Service.ChiTietSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/chi-tiet-san-pham")
@CrossOrigin(origins = "*") //
public class SanPhamChiTietController {
    @Autowired
    private ChiTietSanPhamService chiTietSanPhamService;

    @GetMapping("by-san-pham/{id}")
    public List<ChiTietSanPhamDto> getChiTietBySanPhamId(@PathVariable("id") Long sanPhamId) {
        return chiTietSanPhamService.getBySanPhamId(sanPhamId);
    }
}
