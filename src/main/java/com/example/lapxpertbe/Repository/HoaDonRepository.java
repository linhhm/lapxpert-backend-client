package com.example.lapxpertbe.Repository;

import com.example.lapxpertbe.Enity.HoaDon;
import com.example.lapxpertbe.enums.TrangThaiDonHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, Long> {
    Optional<HoaDon> findByMaHoaDon(String maHoaDon);
    List<HoaDon> findByTrangThaiDonHang(TrangThaiDonHang trangThaiDonHang);
}