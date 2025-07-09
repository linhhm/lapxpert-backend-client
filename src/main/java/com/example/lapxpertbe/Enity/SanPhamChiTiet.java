package com.example.lapxpertbe.Enity;

import com.fasterxml.jackson.annotation.JsonBackReference; // Thêm cái này
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "san_pham_chi_tiet")
public class SanPhamChiTiet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "gia_ban", nullable = false)
    private BigDecimal giaBan;

    @Column(name = "gia_khuyen_mai")
    private BigDecimal giaKhuyenMai;

    @Column(name = "sku", unique = true)
    private String sku;

    @Column(name = "trang_thai", nullable = false)
    private Boolean trangThai = true;

    @Column(name = "ngay_tao", nullable = false)
    private Instant ngayTao;

    @Column(name = "ngay_cap_nhat", nullable = false)
    private Instant ngayCapNhat;

    @Column(name = "nguoi_tao")
    private String nguoiTao;

    @Column(name = "nguoi_cap_nhat")
    private String nguoiCapNhat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "san_pham_id", nullable = false)
    @JsonBackReference
    private SanPham sanPham;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cpu_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "sanPhamChiTiets"})
    private Cpu cpu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gpu_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "sanPhamChiTiets"})
    private Gpu gpu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ram_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "sanPhamChiTiets"})
    private Ram ram;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bo_nho_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "sanPhamChiTiets"})
    private BoNho boNho;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "man_hinh_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "sanPhamChiTiets"})
    private ManHinh manHinh;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mau_sac_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "sanPhamChiTiets"})
    private MauSac mauSac;

    @OneToMany(mappedBy = "sanPhamChiTiet")

    @JsonIgnoreProperties({"sanPhamChiTiet", "dotGiamGia"})
    private List<SanPhamChiTietDotGiamGia> giamGiaTrongDotList;


}