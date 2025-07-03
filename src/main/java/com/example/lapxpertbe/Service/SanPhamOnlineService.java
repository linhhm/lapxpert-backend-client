package com.example.lapxpertbe.Service;

import com.example.lapxpertbe.DTO.SanPhamDTO;
import com.example.lapxpertbe.Enity.SanPham;
import com.example.lapxpertbe.Repository.SanPhameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SanPhamOnlineService {

    private final SanPhameRepository sanPhamOnlineRepository;

    public List<SanPham> getSanPhamActive() {
        return sanPhamOnlineRepository.findByTrangThai(true);
    }

//    private final SanPhameRepository repository;
//
//    public List<SanPhamDTO> locSanPhamTheoGia(Double minGia, Double maxGia) {
//        return repository.getSanPhamVaKhoangGia(minGia, maxGia);
//    }


    public List<SanPhamDTO> getSanPhamKhoangGia() {
        return sanPhamOnlineRepository.getSanPhamVaKhoangGia();
    }
    public Optional<SanPham> findById(Long id) {
        return sanPhamOnlineRepository.findById(id);
    }

}

