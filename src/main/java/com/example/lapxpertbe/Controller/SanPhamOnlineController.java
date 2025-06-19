package com.example.lapxpertbe.Controller;

import com.example.lapxpertbe.DTO.SanPhamDTO;
import com.example.lapxpertbe.Enity.SanPhamOnline;
import com.example.lapxpertbe.Service.SanPhamOnlineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/san-pham-online")

public class SanPhamOnlineController {

    private final SanPhamOnlineService sanPhamOnlineService;


    @GetMapping
    public List<SanPhamOnline> hienThiSanPham() {
        return sanPhamOnlineService.getSanPhamActive();
    }

//    @GetMapping("/san-pham/loc")
//    public List<SanPhamDTO> locSanPham(
//            @RequestParam Double minGia,
//            @RequestParam Double maxGia
//    ) {
//        return sanPhamOnlineService.locSanPhamTheoGia(minGia, maxGia);
//    }

    @GetMapping("/api/san-pham-online/dto") // đổi path
    public List<SanPhamDTO> hienThiSanPham1() {
        return sanPhamOnlineService.getSanPhamKhoangGia();
    }
}



