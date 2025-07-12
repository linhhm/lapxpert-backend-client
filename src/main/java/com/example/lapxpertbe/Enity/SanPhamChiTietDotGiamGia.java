package com.example.lapxpertbe.Enity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "san_pham_chi_tiet_dot_giam_gia")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SanPhamChiTietDotGiamGia {

    @EmbeddedId
    private SanPhamChiTietDotGiamGiaId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("dotGiamGiaId")
    @JoinColumn(name = "dot_giam_gia_id")
    @JsonBackReference
    private DotGiamGia dotGiamGia;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("sanPhamChiTietId")
    @JoinColumn(name = "san_pham_chi_tiet_id")
    @JsonIgnoreProperties({"giamGiaTrongDotList", "dotGiamGiaList", "sanPham"})
    private SanPhamChiTiet sanPhamChiTiet;
}