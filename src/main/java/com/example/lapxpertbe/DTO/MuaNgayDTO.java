package com.example.lapxpertbe.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class MuaNgayDTO {
    private Long sanPhamChiTietId;
    private int soLuong;
    private BigDecimal giaTaiThoiDiemThem;
    private ThanhToanOnlineDTO thongTinKhachHang;
    private String phuongThucThanhToan;
}
