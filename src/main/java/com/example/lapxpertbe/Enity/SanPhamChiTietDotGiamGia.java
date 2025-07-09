package com.example.lapxpertbe.Enity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "san_pham_chi_tiet_dot_giam_gia", schema = "public")
public class SanPhamChiTietDotGiamGia {
    @EmbeddedId
    private SanPhamChiTietDotGiamGiaId id;

    @MapsId("dotGiamGiaId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "dot_giam_gia_id", nullable = false)
    private DotGiamGia dotGiamGia;

    @MapsId("sanPhamChiTietId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "san_pham_chi_tiet_id", nullable = false)
    @JsonIgnoreProperties({"chiTietGiamGiaList", "dotGiamGiaList"}) // Đảm bảo bỏ qua các list có thể gây vòng lặp trong SanPhamChiTiet
    private SanPhamChiTiet sanPhamChiTiet;

}