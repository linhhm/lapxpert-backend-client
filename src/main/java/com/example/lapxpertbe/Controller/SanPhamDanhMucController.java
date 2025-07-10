// com.example.lapxpertbe.Controller.SanPhamDanhMucController.java
package com.example.lapxpertbe.Controller;

import com.example.lapxpertbe.DTO.SanPhamDanhMucDTO;
import com.example.lapxpertbe.Enity.SanPhamDanhMuc;
import com.example.lapxpertbe.Repository.SanPhamDanhMucRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/san-pham-danh-muc")
public class           SanPhamDanhMucController {

    @Autowired
    private SanPhamDanhMucRepository sanPhamDanhMucRepository;



    @GetMapping("/all-ids")
    public ResponseEntity<List<SanPhamDanhMucDTO>> getAllSanPhamDanhMucsIds() {
        List<SanPhamDanhMuc> sanPhamDanhMucs = sanPhamDanhMucRepository.findAll();
        if (sanPhamDanhMucs.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        // Ánh xạ từ Entity sang DTO
        List<SanPhamDanhMucDTO> dtos = sanPhamDanhMucs.stream()
                .map(SanPhamDanhMucDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }


}