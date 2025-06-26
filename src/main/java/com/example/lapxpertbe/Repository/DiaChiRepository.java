package com.example.lapxpertbe.Repository;

import com.example.lapxpertbe.Enity.DiaChi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface DiaChiRepository extends JpaRepository<DiaChi, Long> {
    Optional<DiaChi> findFirstByDuongAndPhuongXaAndQuanHuyenAndTinhThanh(
            String duong,
            String phuongXa,
            String quanHuyen,
            String tinhThanh
    );
}