package com.example.lapxpertbe.Enity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "phieu_giam_gia")
public class PhieuGiamGia {
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

    @Column(name = "ma_phieu_giam_gia", nullable = false, length = 50)
    private String maPhieuGiamGia;

    @Column(name = "loai_giam_gia", nullable = false)
    private String loaiGiamGia;

    @Column(name = "trang_thai", nullable = false)
    private String trangThai;

    @Column(name = "gia_tri_giam", nullable = false, precision = 15, scale = 2)
    private BigDecimal giaTriGiam;

    @Column(name = "gia_tri_don_hang_toi_thieu", precision = 15, scale = 2)
    private BigDecimal giaTriDonHangToiThieu;

    @Column(name = "ngay_bat_dau", nullable = false)
    private Instant ngayBatDau;

    @Column(name = "ngay_ket_thuc", nullable = false)
    private Instant ngayKetThuc;

    @Column(name = "mo_ta", length = Integer.MAX_VALUE)
    private String moTa;

    @Column(name = "so_luong_ban_dau", nullable = false)
    private Integer soLuongBanDau;

    @Column(name = "so_luong_da_dung")
    private Integer soLuongDaDung;
    @Column(name = "phieu_rieng_tu", nullable = false)
    private boolean phieuRiengTu;

}