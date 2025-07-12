package com.example.lapxpertbe.Service;

import com.example.lapxpertbe.Enity.DotGiamGia;
import com.example.lapxpertbe.Repository.DotGiamGiaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DotGiamGiaService {

    @Autowired
    private DotGiamGiaRepository dotGiamGiaRepository; // Hoặc tên repository của bạn

    public List<DotGiamGia> findAll() {
        return dotGiamGiaRepository.findAll();
    }



    @Transactional
    public Optional<DotGiamGia> findByIdWithDetails(Long id) {
        return dotGiamGiaRepository.findByIdWithDetails(id);
    }
}