package com.example.lapxpertbe.Repository;

import com.example.lapxpertbe.Enity.SanPhamChiTietDotGiamGia;
import com.example.lapxpertbe.Enity.SanPhamChiTietDotGiamGiaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SanPhamChiTietDotGiamGiaRepository extends JpaRepository<SanPhamChiTietDotGiamGia, SanPhamChiTietDotGiamGiaId> {
    List<SanPhamChiTietDotGiamGia> findByDotGiamGia_Id(Long dotGiamGiaId);
}