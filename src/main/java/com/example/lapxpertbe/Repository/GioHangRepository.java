package com.example.lapxpertbe.Repository;

import com.example.lapxpertbe.Enity.GioHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GioHangRepository extends JpaRepository<GioHang, Long> {
    // 🔍 Tìm giỏ hàng theo người dùng đã đăng nhập
    Optional<GioHang> findByNguoiDungId(Long nguoiDungId);

    // 🔍 Tìm giỏ hàng theo sessionId (khách chưa đăng nhập)
    Optional<GioHang> findBySessionId(String sessionId);
}