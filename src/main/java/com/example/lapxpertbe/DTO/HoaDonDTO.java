package com.example.lapxpertbe.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HoaDonDTO {
    private String maHoaDon;
    private Instant ngayTao;
    private String trangThai;
    private BigDecimal tongTien;
    private List<HoaDonChiTietDTO> chiTietHoaDons;
}
