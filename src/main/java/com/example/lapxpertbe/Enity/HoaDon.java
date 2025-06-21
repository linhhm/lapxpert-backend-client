package com.example.lapxpertbe.Enity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "hoa_don")
public class HoaDon {
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

    @Column(name = "ma_hoa_don", nullable = false)
    private String maHoaDon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dia_chi_giao_hang_id")
    private DiaChi diaChiGiaoHang;

    @Column(name = "nguoi_nhan_ten")
    private String nguoiNhanTen;

    @Column(name = "nguoi_nhan_sdt", length = 20)
    private String nguoiNhanSdt;

    @Column(name = "tong_tien_hang", precision = 15, scale = 2)
    private BigDecimal tongTienHang;

    @Column(name = "gia_tri_giam_gia_voucher", nullable = false, precision = 15, scale = 2)
    private BigDecimal giaTriGiamGiaVoucher;

    @Column(name = "phi_van_chuyen", nullable = false, precision = 15, scale = 2)
    private BigDecimal phiVanChuyen;

    @Column(name = "tong_thanh_toan", nullable = false, precision = 15, scale = 2)
    private BigDecimal tongThanhToan;

    @Column(name = "trang_thai_don_hang", nullable = false)
    private String trangThaiDonHang;

    @Column(name = "trang_thai_thanh_toan", nullable = false)
    private String trangThaiThanhToan;

    @Column(name = "loai_hoa_don", nullable = false)
    private String loaiHoaDon;

    @Column(name = "ma_van_don", length = 100)
    private String maVanDon;

    @Column(name = "ngay_du_kien_giao_hang")
    private Instant ngayDuKienGiaoHang;

}