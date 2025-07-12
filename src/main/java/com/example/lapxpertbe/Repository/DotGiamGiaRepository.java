package com.example.lapxpertbe.Repository;

import com.example.lapxpertbe.Enity.DotGiamGia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DotGiamGiaRepository extends JpaRepository<DotGiamGia, Long> {

    @Query("SELECT d FROM DotGiamGia d LEFT JOIN FETCH d.chiTietGiamGiaList dcl LEFT JOIN FETCH dcl.sanPhamChiTiet WHERE d.id = :id")
    Optional<DotGiamGia> findByIdWithDetails(Long id);
}