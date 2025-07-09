package com.example.lapxpertbe.Repository;

import com.example.lapxpertbe.Enity.SanPhamDanhMuc;
import com.example.lapxpertbe.Enity.SanPhamDanhMucId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SanPhamDanhMucRepository extends JpaRepository<SanPhamDanhMuc, SanPhamDanhMucId> {

    @Query("SELECT sdm FROM SanPhamDanhMuc sdm JOIN FETCH sdm.sanPham WHERE sdm.danhMuc.id = :id")
    List<SanPhamDanhMuc> findAllByDanhMucId(@Param("id") Long id);


    List<SanPhamDanhMuc> findAllBySanPhamId(Long sanPhamId);
}
