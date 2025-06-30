package com.example.lapxpertbe.Enity;

import com.example.lapxpertbe.enums.LoaiHoaDon;
import com.example.lapxpertbe.enums.TrangThaiDonHang;
import com.example.lapxpertbe.enums.TrangThaiThanhToan;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "hoa_don")
public class HoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(name = "gia_tri_giam_gia_voucher", precision = 15, scale = 2)
    private BigDecimal giaTriGiamGiaVoucher;

    @Column(name = "phi_van_chuyen", precision = 15, scale = 2)
    private BigDecimal phiVanChuyen;

    @Column(name = "tong_thanh_toan", precision = 15, scale = 2)
    private BigDecimal tongThanhToan;

    @Column(name = "trang_thai_don_hang", nullable = false)
    @Enumerated(EnumType.STRING)
    private TrangThaiDonHang trangThaiDonHang;

    @Column(name = "trang_thai_thanh_toan", nullable = false)
    @Enumerated(EnumType.STRING)
    private TrangThaiThanhToan trangThaiThanhToan;

    @Column(name = "loai_hoa_don", nullable = false)
    @Enumerated(EnumType.STRING)
    private LoaiHoaDon loaiHoaDon;

    @Column(name = "ma_van_don", length = 100)
    private String maVanDon;

    @Column(name = "ngay_du_kien_giao_hang")
    private Instant ngayDuKienGiaoHang;
    @ManyToMany
    @JoinTable(
            name = "hoa_don_phieu_giam_gia",
            joinColumns = @JoinColumn(name = "hoa_don_id"),
            inverseJoinColumns = @JoinColumn(name = "phieu_giam_gia_id")
    )
    private List<PhieuGiamGia> phieuGiamGias = new ArrayList<>();

    @OneToMany(mappedBy = "hoaDon", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<HoaDonChiTiet> hoaDonChiTiets;
}