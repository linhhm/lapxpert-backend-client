package com.example.lapxpertbe.Service;

import com.example.lapxpertbe.DTO.SanPhamBanChayDTO;
import com.example.lapxpertbe.DTO.SanPhamDTO;
import com.example.lapxpertbe.Enity.SanPham;
import com.example.lapxpertbe.Enity.SanPhamDanhMuc;
import com.example.lapxpertbe.Repository.SanPhamDanhMucRepository;
import com.example.lapxpertbe.enums.TrangThaiDonHang;
import com.example.lapxpertbe.Repository.SanPhamRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SanPhamOnlineService {

    private final SanPhamRepository sanPhamOnlineRepository;
    private final SanPhamDanhMucRepository sanPhamDanhMucRepository;


    public List<SanPham> getSanPhamActive() {
        return sanPhamOnlineRepository.findByTrangThai(true);
    }

    public List<SanPhamDTO> getSanPhamKhoangGia() {
        return sanPhamOnlineRepository.getSanPhamVaKhoangGia();
    }

    public List<SanPham> getProductsByCategoryId(Long categoryId) {
        List<SanPhamDanhMuc> sanPhamDanhMucs = sanPhamDanhMucRepository.findAllByDanhMucId(categoryId);
        return sanPhamDanhMucs.stream()
                .map(SanPhamDanhMuc::getSanPham)
                .collect(Collectors.toList());
    }

    public List<SanPhamBanChayDTO> getTopSellingProducts(int limit) {
        TrangThaiDonHang trangThaiHoanThanh = TrangThaiDonHang.HOAN_THANH;
        Pageable pageable = PageRequest.of(0, limit);
        return sanPhamOnlineRepository.findTopSellingProducts(trangThaiHoanThanh, pageable);
    }

}