package com.example.lapxpertbe.Service;

import com.example.lapxpertbe.Enity.SanPhamChiTietDotGiamGia;
import com.example.lapxpertbe.Repository.SanPhamChiTietDotGiamGiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SanPhamChiTietDotGiamGiaService {

    @Autowired
    private SanPhamChiTietDotGiamGiaRepository repository;

    public List<SanPhamChiTietDotGiamGia> getByDotGiamGia(Long dotGiamGiaId) {
        return repository.findByDotGiamGia_Id(dotGiamGiaId);
    }

}