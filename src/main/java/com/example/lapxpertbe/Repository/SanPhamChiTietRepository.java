package com.example.lapxpertbe.Repository;

import com.example.lapxpertbe.Enity.SanPhamChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SanPhamChiTietRepository extends JpaRepository<SanPhamChiTiet, Long> {
    List<SanPhamChiTiet> findBySanPham_Id(Long sanPhamId);
}