package com.example.lapxpertbe.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.example.lapxpertbe.Enity.SanPhamDanhMuc; // Import SanPhamDanhMuc entity

@Data // Lombok annotation to generate getters, setters, toString, equals, hashCode
@NoArgsConstructor // Lombok annotation to generate a no-argument constructor
@AllArgsConstructor // Lombok annotation to generate an all-arguments constructor
public class SanPhamDanhMucDTO {
    private Long danhMucId;
    private Long sanPhamId;

    // Static factory method to create DTO from entity
    public static SanPhamDanhMucDTO fromEntity(SanPhamDanhMuc entity) {
        return new SanPhamDanhMucDTO(entity.getDanhMuc().getId(), entity.getSanPham().getId());
    }
}