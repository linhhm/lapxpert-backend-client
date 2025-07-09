package com.example.lapxpertbe.Enity;

import com.fasterxml.jackson.annotation.JsonBackReference; // <-- Thêm cái này
import com.fasterxml.jackson.annotation.JsonIgnoreProperties; // Vẫn cần giữ cái này cho SanPhamChiTiet
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

    @ManyToOne
    @MapsId("dotGiamGiaId")
    @JoinColumn(name = "dot_giam_gia_id")
    @JsonBackReference // <-- Đổi từ @JsonIgnore sang @JsonBackReference
    private DotGiamGia dotGiamGia;

    @ManyToOne
    @MapsId("sanPhamChiTietId")
    @JoinColumn(name = "san_pham_chi_tiet_id")
    // Giữ nguyên JsonIgnoreProperties cho sanPhamChiTiet,
    // vì SanPhamChiTiet có thể có mối quan hệ ngược lại với chiTietGiamGiaList,
    // hoặc bất kỳ List nào khác có thể gây vòng lặp.
    @JsonIgnoreProperties({"chiTietGiamGiaList", "dotGiamGiaList"}) // Đảm bảo bỏ qua các list có thể gây vòng lặp trong SanPhamChiTiet
    private SanPhamChiTiet sanPhamChiTiet;
}