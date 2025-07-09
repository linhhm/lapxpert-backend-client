package com.example.lapxpertbe.Enity;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SanPhamChiTietDotGiamGiaId implements Serializable {
    @Column(name = "dot_giam_gia_id")
    private Long dotGiamGiaId;

    @Column(name = "san_pham_chi_tiet_id")
    private Long sanPhamChiTietId;
}
