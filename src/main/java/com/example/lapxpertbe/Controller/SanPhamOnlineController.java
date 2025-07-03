package com.example.lapxpertbe.Controller;

import com.example.lapxpertbe.DTO.SanPhamDTO;
import com.example.lapxpertbe.Enity.SanPham;
import com.example.lapxpertbe.Service.SanPhamOnlineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
}



