package com.example.lapxpertbe.DTO;

import com.example.lapxpertbe.Enity.SanPhamChiTiet;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
@Data
public class GioHangIdDTO {
    private Long gioHangId;
    private SanPhamChiTiet sanPhamChiTiet;
    private int soLuong;
    private BigDecimal giaTaiThoiDiemThem;
    private List<String> serialNumbers; // nếu có dùng

    public GioHangIdDTO(Long gioHangId, SanPhamChiTiet spct, int soLuong, BigDecimal gia, List<String> serialNumbers) {
        this.gioHangId = gioHangId;
        this.sanPhamChiTiet = spct;
        this.soLuong = soLuong;
        this.giaTaiThoiDiemThem = gia;
        this.serialNumbers = serialNumbers;
    }

}
