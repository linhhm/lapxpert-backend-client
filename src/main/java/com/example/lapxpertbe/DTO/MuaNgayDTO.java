package com.example.lapxpertbe.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class MuaNgayDTO {
    private Long sanPhamChiTietId;
    private int soLuong;
    private BigDecimal giaTaiThoiDiemThem;
    private ThanhToanOnlineDTO thongTinKhachHang;
    private String phuongThucThanhToan;
    private List<SanPhamMuaNgayDTO> danhSachSanPham;
}
