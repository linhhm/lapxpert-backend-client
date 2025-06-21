package com.example.lapxpertbe.Repository;

import com.example.lapxpertbe.Enity.GioHangChiTiet;
import com.example.lapxpertbe.Enity.HoaDonChiTiet;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface HoaDonChiTietRepository extends JpaRepository<HoaDonChiTiet, Long> {
}