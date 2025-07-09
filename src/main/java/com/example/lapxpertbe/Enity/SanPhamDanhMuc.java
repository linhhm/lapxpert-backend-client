package com.example.lapxpertbe.Enity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Table(name = "san_pham_danh_muc")

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class SanPhamDanhMuc {

    @EmbeddedId
    private SanPhamDanhMucId id;

    @ManyToOne
    @MapsId("sanPhamId")
    @JoinColumn(name = "san_pham_id")
    private SanPham sanPham;

    @ManyToOne
    @MapsId("danhMucId")
    @JoinColumn(name = "danh_muc_id")
    private DanhMuc danhMuc;
}


