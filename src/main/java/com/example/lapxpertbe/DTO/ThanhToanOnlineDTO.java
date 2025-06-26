package com.example.lapxpertbe.DTO;

import lombok.Data;

import java.util.List;
@Data
public class ThanhToanOnlineDTO {
    private String hoTen;
    private String email;
    private String soDienThoai;
    private String duong;
    private String phuongXa;
    private String quanHuyen;
    private String tinhThanh;
    private List<Long> sanPhamChiTietIds;
    private List<Integer> soLuong;
    private String phuongThucThanhToan;

}
