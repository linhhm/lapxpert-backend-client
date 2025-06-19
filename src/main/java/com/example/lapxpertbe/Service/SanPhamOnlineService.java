package com.example.lapxpertbe.Service;

import com.example.lapxpertbe.DTO.SanPhamDTO;
import com.example.lapxpertbe.Enity.SanPhamOnline;
import com.example.lapxpertbe.Repository.SanPhamOnlineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SanPhamOnlineService {

    private final SanPhamOnlineRepository sanPhamOnlineRepository;

    public List<SanPhamOnline> getSanPhamActive() {
        return sanPhamOnlineRepository.findByTrangThai(true);
    }

//    private final SanPhamOnlineRepository repository;
//
//    public List<SanPhamDTO> locSanPhamTheoGia(Double minGia, Double maxGia) {
//        return repository.getSanPhamVaKhoangGia(minGia, maxGia);
//    }


    public List<SanPhamDTO> getSanPhamKhoangGia() {
        return sanPhamOnlineRepository.getSanPhamVaKhoangGia();
    }

}

