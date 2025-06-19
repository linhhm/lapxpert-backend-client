package com.example.lapxpertbe.Enity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "san_pham")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SanPhamOnline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma_san_pham") // đúng tên cột thật
    private String maSanPham;

    @Column(name = "ten_san_pham")
    private String tenSanPham;

    @Column(name = "ngay_tao")
    private LocalDateTime ngayTao;

    @Column(name = "ngay_cap_nhat")
    private LocalDateTime ngayCapNhat;

    @Column(name = "nguoi_tao")
    private String nguoiTao;

    @Column(name = "nguoi_cap_nhat")
    private String nguoiCapNhat;

    @Column(name = "hinh_anh")
    private String hinhAnh;

    @Column(name = "ngay_ra_mat")
    private LocalDate ngayRaMat;

    @Column(name = "trang_thai")
    private Boolean trangThai;


    @Column(name = "mo_ta")
    private String moTa;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "thuong_hieu_id") // đúng FK trong bảng san_pham
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // thêm cái này
    private ThuongHieu thuongHieu;

    @OneToMany(mappedBy = "sanPham", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"sanPham"}) // để tránh vòng lặp vô hạn khi serialize JSON
    private List<ChiTietSanPham> chiTietSanPhams;





}

