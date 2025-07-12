package com.example.lapxpertbe.Service;

import com.example.lapxpertbe.Enity.PhieuGiamGia;
import com.example.lapxpertbe.Repository.PhieuGiamGiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PhieuGiamGiaSerice {
    @Autowired
    private PhieuGiamGiaRepository repository;


    public List<PhieuGiamGia> findAll() {
        return repository.findAll();
    }

    public List<PhieuGiamGia> getPhieuTrangChu() {
        Instant now = Instant.now();
        List<PhieuGiamGia> allPhieu = repository.findAll();

        return allPhieu.stream()
                .filter(phieu -> !phieu.isPhieuRiengTu())
                .filter(phieu -> phieu.getNgayBatDau() != null && phieu.getNgayKetThuc() != null)
                .filter(phieu -> !phieu.getNgayBatDau().isAfter(now))
                .filter(phieu -> phieu.getNgayKetThuc().isAfter(now))
                .filter(phieu -> "DA_DIEN_RA".equals(phieu.getTrangThai()))
                .collect(Collectors.toList());
    }

    public List<PhieuGiamGia> getTopVouchersByDiscount(int limit) {
        Instant currentTime = Instant.now();
        List<PhieuGiamGia> sortedVouchers = repository.findTopActivePublicVouchersByDiscountValue(currentTime);

        if (sortedVouchers.size() > limit) {
            return sortedVouchers.stream()
                    .limit(limit)
                    .collect(Collectors.toList());
        }
        return sortedVouchers;
    }
}