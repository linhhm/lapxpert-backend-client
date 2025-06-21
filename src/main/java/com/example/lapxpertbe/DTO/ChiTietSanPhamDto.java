package com.example.lapxpertbe.DTO;

import com.example.lapxpertbe.Enity.SanPhamChiTiet;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ChiTietSanPhamDto {
    private Long id;
    private Long ramId;
    private String moTaRam;

    private Long mauSacId;
    private String moTaMauSac;

    private Long cpuId;
    private String moTaCpu;

    private Long gpuId;
    private String moTaGpu;

    private Long boNhoId;
    private String moTaBoNho;

    private BigDecimal giaBan;
    private BigDecimal giaKhuyenMai;

    public static ChiTietSanPhamDto fromEntity(SanPhamChiTiet e) {
        ChiTietSanPhamDto dto = new ChiTietSanPhamDto();
        dto.setId(e.getId());

//        if (e.getSanPham() != null) {
//            dto.setTenSanPham(e.getSanPham().getTenSanPham());
//        }
        if (e.getRam() != null) {
            dto.setRamId(e.getRam().getId());
            dto.setMoTaRam(e.getRam().getMoTaRam());
        }

        if (e.getMauSac() != null) {
            dto.setMauSacId(e.getMauSac().getId());
            dto.setMoTaMauSac(e.getMauSac().getMoTaMauSac());
        }

        if (e.getCpu() != null) {
            dto.setCpuId(e.getCpu().getId());
            dto.setMoTaCpu(e.getCpu().getMoTaCpu());
        }

        if (e.getGpu() != null) {
            dto.setGpuId(e.getGpu().getId());
            dto.setMoTaGpu(e.getGpu().getMoTaGpu());
        }

        if (e.getBoNho() != null) {
            dto.setBoNhoId(e.getBoNho().getId());
            dto.setMoTaBoNho(e.getBoNho().getMoTaBoNho());
        }

        dto.setGiaBan(e.getGiaBan());
        dto.setGiaKhuyenMai(e.getGiaKhuyenMai());
        return dto;
    }
}
