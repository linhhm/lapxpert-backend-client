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

//    public Optional<PhieuGiamGia> findByCodeValid(String maGiamGia) {
//        Instant now = Instant.now();
//        return repository.findByMaGiamGiaAndNgayBatDauBeforeAndNgayKetThucAfter(maGiamGia, now, now);
//    }

    public List<PhieuGiamGia> findAll() {
        return repository.findAll();
    }
    public List<PhieuGiamGia> getPhieuTrangChu() {
        Instant now = Instant.now(); // Lấy thời gian hiện tại (UTC)
        List<PhieuGiamGia> allPhieu = repository.findAll(); // Lấy tất cả các phiếu từ DB

        return allPhieu.stream()
                .filter(phieu -> !phieu.isPhieuRiengTu()) // Không phải phiếu riêng tư
                .filter(phieu -> phieu.getNgayBatDau() != null && phieu.getNgayKetThuc() != null) // Đảm bảo ngày không null
                .filter(phieu -> !phieu.getNgayBatDau().isAfter(now)) // Đã bắt đầu (nghĩa là ngày bắt đầu <= now)
                .filter(phieu -> phieu.getNgayKetThuc().isAfter(now)) // Chưa kết thúc (nghĩa là ngày kết thúc > now)
                .filter(phieu -> "DA_DIEN_RA".equals(phieu.getTrangThai())) // Trạng thái đang diễn ra
                .collect(Collectors.toList());
    }
}
