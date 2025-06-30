package com.example.lapxpertbe.Service;

import com.example.lapxpertbe.DTO.HoaDonChiTietDTO;
import com.example.lapxpertbe.DTO.HoaDonDTO;
import com.example.lapxpertbe.Enity.HoaDon;
import com.example.lapxpertbe.Enity.SerialNumberHoaDonChiTiet;
import com.example.lapxpertbe.Repository.HoaDonRepository;
import com.example.lapxpertbe.Repository.SerialNumberHoaDonChiTietRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HoaDonService {
    private final HoaDonRepository hoaDonRepository;
    private final SerialNumberHoaDonChiTietRepository serialNumberHoaDonChiTietRepository;

    public HoaDonDTO getByMaHoaDon(String maHoaDon) {
        HoaDon hoaDon = hoaDonRepository.findByMaHoaDon(maHoaDon)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy hóa đơn"));

        HoaDonDTO dto = new HoaDonDTO();
        dto.setMaHoaDon(hoaDon.getMaHoaDon());
        dto.setNgayTao(hoaDon.getNgayTao());
        dto.setTrangThai(hoaDon.getTrangThaiDonHang().name());
        dto.setTongTien(hoaDon.getTongTienHang());

        List<HoaDonChiTietDTO> chiTietDtos = hoaDon.getHoaDonChiTiets().stream().map(ct -> {
            HoaDonChiTietDTO ctd = new HoaDonChiTietDTO();
            ctd.setSoLuong(ct.getSoLuong());
            ctd.setGia(ct.getGiaBan());

            List<SerialNumberHoaDonChiTiet> lienKet = serialNumberHoaDonChiTietRepository.findByHoaDonChiTiet_Id(ct.getId());
            List<String> serials = lienKet.stream()
                    .map(sn -> sn.getSerialNumber().getSerialNumberValue())
                    .collect(Collectors.toList());
            ctd.setSerialNumbers(serials);

            ctd.setTenSanPham(ct.getSanPhamChiTiet().getSanPham().getTenSanPham());
            return ctd;
        }).toList();

        dto.setChiTietHoaDons(chiTietDtos);
        return dto;
    }

}
