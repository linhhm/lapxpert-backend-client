package com.example.lapxpertbe.Service;

import com.example.lapxpertbe.Enity.DotGiamGia;
import com.example.lapxpertbe.Repository.DotGiamGiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DotGiamGiaService {

    @Autowired
    private DotGiamGiaRepository dotGiamGiaRepository; // Hoặc tên repository của bạn

    public List<DotGiamGia> findAll() {
        return dotGiamGiaRepository.findAll();
    }
}