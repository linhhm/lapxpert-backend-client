package com.example.lapxpertbe.DTO;

import lombok.Data;

import java.util.List;
@Data
public class ThanhToanOnlineDTO {
    private String hoTen;
    private String email;
    private String soDienThoai;
    private String diaChiGiaoHang;
    private List<Long> sanPhamChiTietIds;
    private List<Integer> soLuong;
    private String phuongThucThanhToan;
}
