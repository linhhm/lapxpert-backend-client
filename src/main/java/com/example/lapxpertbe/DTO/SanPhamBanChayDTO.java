package com.example.lapxpertbe.DTO;

import java.math.BigDecimal; // Import BigDecimal

public class SanPhamBanChayDTO {
    private Long id;
    private String maSanPham;
    private String tenSanPham;
    private String hinhAnh;
    private String tenThuongHieu;
    private Long tongSoLuongBan;

    private BigDecimal giaBan;
    private BigDecimal giaKhuyenMai;


    public SanPhamBanChayDTO(
            Long id,
            String maSanPham,
            String tenSanPham,
            String hinhAnh,
            String tenThuongHieu,
            Long tongSoLuongBan,
            BigDecimal giaBan,
            BigDecimal giaKhuyenMai
    ) {
        this.id = id;
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.hinhAnh = hinhAnh;
        this.tenThuongHieu = tenThuongHieu;
        this.tongSoLuongBan = tongSoLuongBan;
        this.giaBan = giaBan;
        this.giaKhuyenMai = giaKhuyenMai;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getMaSanPham() { return maSanPham; }
    public void setMaSanPham(String maSanPham) { this.maSanPham = maSanPham; }
    public String getTenSanPham() { return tenSanPham; }
    public void setTenSanPham(String tenSanPham) { this.tenSanPham = tenSanPham; }
    public String getHinhAnh() { return hinhAnh; }
    public void setHinhAnh(String hinhAnh) { this.hinhAnh = hinhAnh; }
    public String getTenThuongHieu() { return tenThuongHieu; }
    public void setTenThuongHieu(String tenThuongHieu) { this.tenThuongHieu = tenThuongHieu; }
    public Long getTongSoLuongBan() { return tongSoLuongBan; }
    public void setTongSoLuongBan(Long tongSoLuongBan) { this.tongSoLuongBan = tongSoLuongBan; }
    public BigDecimal getGiaBan() { return giaBan; }
    public void setGiaBan(BigDecimal giaBan) { this.giaBan = giaBan; }
    public BigDecimal getGiaKhuyenMai() { return giaKhuyenMai; }
    public void setGiaKhuyenMai(BigDecimal giaKhuyenMai) { this.giaKhuyenMai = giaKhuyenMai; }
}