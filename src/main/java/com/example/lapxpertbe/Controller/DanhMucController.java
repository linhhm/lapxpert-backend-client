package com.example.lapxpertbe.Controller;

import com.example.lapxpertbe.Enity.DanhMuc;
import com.example.lapxpertbe.Service.ChiTietSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/danh-muc")
@CrossOrigin("*")
public class DanhMucController {


    @Autowired
    private ChiTietSanPhamService chiTietSanPhamService;

    @GetMapping
    public List<DanhMuc> getAllDanhMucs() {
        return chiTietSanPhamService.getAllDanhMucs();
    }


}