package com.example.lapxpertbe.DTO;



import java.math.BigDecimal;

public class SanPhamDTO {
    private Long id;
    private String tenSanPham;
    private String hinhAnh;
    private String tenThuongHieu;
    private BigDecimal giaMin;
    private BigDecimal giaMax;

    public SanPhamDTO(Long id, String tenSanPham, String hinhAnh, String tenThuongHieu, BigDecimal giaMin, BigDecimal giaMax) {
        this.id = id;
        this.tenSanPham = tenSanPham;
        this.hinhAnh = hinhAnh;
        this.tenThuongHieu = tenThuongHieu;
        this.giaMin = giaMin;
        this.giaMax = giaMax;
    }

    // Getters và Setters (hoặc dùng @Data nếu bạn dùng Lombok)
    public Long getId() {
        return id;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public String getTenThuongHieu() {
        return tenThuongHieu;
    }

    public BigDecimal getGiaMin() {
        return giaMin;
    }

    public BigDecimal getGiaMax() {
        return giaMax;
    }
}


