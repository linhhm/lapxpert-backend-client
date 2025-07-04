package com.example.lapxpertbe.Enity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "san_pham_danh_muc")
public class SanPhamDanhMuc {
    @EmbeddedId
    private SanPhamDanhMucId id;

    @MapsId("sanPhamId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "san_pham_id", nullable = false)
    private SanPham sanPham;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("danhMucId")
    @JoinColumn(name = "danh_muc_id")
    private DanhMuc danhMuc;
}