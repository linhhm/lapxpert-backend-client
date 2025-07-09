package com.example.lapxpertbe.Enity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "dot_giam_gia", schema = "public")
public class DotGiamGia {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "ngay_tao", nullable = false)
    private Instant ngayTao;

    @Column(name = "ngay_cap_nhat", nullable = false)
    private Instant ngayCapNhat;

    @Column(name = "nguoi_tao", length = 100)
    private String nguoiTao;

    @Column(name = "nguoi_cap_nhat", length = 100)
    private String nguoiCapNhat;

    @Column(name = "ma_dot_giam_gia", nullable = false, length = 50)
    private String maDotGiamGia;

    @Column(name = "ten_dot_giam_gia", nullable = false)
    private String tenDotGiamGia;

    @Column(name = "phan_tram_giam", nullable = false, precision = 5, scale = 2)
    private BigDecimal phanTramGiam;

    @Column(name = "ngay_bat_dau", nullable = false)
    private Instant ngayBatDau;

    @Column(name = "ngay_ket_thuc", nullable = false)
    private Instant ngayKetThuc;

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