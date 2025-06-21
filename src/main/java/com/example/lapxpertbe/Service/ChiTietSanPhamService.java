package com.example.lapxpertbe.Service;

import com.example.lapxpertbe.DTO.ChiTietSanPhamDto;
import com.example.lapxpertbe.Enity.SanPhamChiTiet;
import com.example.lapxpertbe.Repository.SanPhamChiTietRepository;
import com.example.lapxpertbe.Repository.SanPhameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChiTietSanPhamService {
    @Autowired
    private SanPhamChiTietRepository sanPhamChiTietRepository;

    public List<ChiTietSanPhamDto> getBySanPhamId(Long sanPhamId) {
        List<SanPhamChiTiet> list = sanPhamChiTietRepository.findBySanPham_Id(sanPhamId);
        return list.stream()
                .map(ChiTietSanPhamDto::fromEntity)
                .toList();
    }
}
