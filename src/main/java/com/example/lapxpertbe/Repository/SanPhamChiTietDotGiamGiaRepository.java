package com.example.lapxpertbe.Repository;

import com.example.lapxpertbe.Enity.SanPhamChiTietDotGiamGia;
import com.example.lapxpertbe.Enity.SanPhamChiTietDotGiamGiaId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SanPhamChiTietDotGiamGiaRepository extends JpaRepository<SanPhamChiTietDotGiamGia, SanPhamChiTietDotGiamGiaId> {

    // Tìm các sản phẩm theo đợt giảm giá
    List<SanPhamChiTietDotGiamGia> findByDotGiamGia_Id(Long dotGiamGiaId);
}
