package com.example.lapxpertbe.Enity;

import com.fasterxml.jackson.annotation.JsonManagedReference; // <-- Thêm cái này
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "dot_giam_gia")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DotGiamGia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ngay_tao")
    private LocalDateTime ngayTao;

    @Column(name = "ngay_cap_nhat")
    private LocalDateTime ngayCapNhat;

    @Column(name = "nguoi_tao")
    private String nguoiTao;

    @Column(name = "nguoi_cap_nhat")
    private String nguoiCapNhat;

    @Column(name = "ma_dot_giam_gia")
    private String maDotGiamGia;

    @Column(name = "ten_dot_giam_gia")
    private String tenDotGiamGia;

    @Column(name = "phan_tram_giam")
    private Integer phanTramGiam;

    @Column(name = "ngay_bat_dau")
    private LocalDateTime ngayBatDau;

    @Column(name = "ngay_ket_thuc")
    private LocalDateTime ngayKetThuc;

    @Column(name = "trang_thai")
    private String trangThaiRaw;

    @Transient
    public boolean isTrangThai() {
        return "DA_DIEN_RA".equalsIgnoreCase(
                trangThaiRaw != null ? trangThaiRaw.trim() : ""
        );
    }

    @OneToMany(mappedBy = "dotGiamGia", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference // Đây là "cha", giữ lại dữ liệu của các "con"
    private List<SanPhamChiTietDotGiamGia> chiTietGiamGiaList;
}