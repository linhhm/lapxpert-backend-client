package com.example.lapxpertbe.Repository;

import com.example.lapxpertbe.Enity.GioHangChiTiet;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GioHangChiTietRepository extends JpaRepository<GioHangChiTiet, Long> {
    Optional<GioHangChiTiet> findByGioHangIdAndSanPhamChiTietId(Long gioHangId, Long sanPhamChiTietId);
    List<GioHangChiTiet> findByGioHangId(Long gioHangId);


    void deleteByGioHangIdAndSanPhamChiTietId(@Param("gioHangId") Long gioHangId,
                                              @Param("sanPhamChiTietId") Long sanPhamChiTietId);

    @Modifying
    @Transactional
    @Query("DELETE FROM GioHangChiTiet g WHERE g.gioHang.id = :gioHangId")
    void deleteByGioHangId(@Param("gioHangId") Long gioHangId);
}