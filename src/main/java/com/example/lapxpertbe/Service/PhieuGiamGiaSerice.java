package com.example.lapxpertbe.Service;

import com.example.lapxpertbe.Enity.PhieuGiamGia;
import com.example.lapxpertbe.Repository.PhieuGiamGiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PhieuGiamGiaSerice {
    @Autowired
    private PhieuGiamGiaRepository repository;

//    public Optional<PhieuGiamGia> findByCodeValid(String maGiamGia) {
//        Instant now = Instant.now();
//        return repository.findByMaGiamGiaAndNgayBatDauBeforeAndNgayKetThucAfter(maGiamGia, now, now);
//    }

    public List<PhieuGiamGia> findAll() {
        return repository.findAll();
    }
}
