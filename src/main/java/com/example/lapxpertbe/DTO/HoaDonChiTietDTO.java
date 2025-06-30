package com.example.lapxpertbe.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HoaDonChiTietDTO {
    private int soLuong;
    private BigDecimal gia;
    private String tenSanPham;
    private List<String> serialNumbers;
}
