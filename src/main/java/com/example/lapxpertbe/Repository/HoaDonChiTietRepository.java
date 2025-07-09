package com.example.lapxpertbe.Repository;

import com.example.lapxpertbe.Enity.GioHangChiTiet;
import com.example.lapxpertbe.Enity.HoaDonChiTiet;
import com.example.lapxpertbe.enums.TrangThaiDonHang;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface HoaDonChiTietRepository extends JpaRepository<HoaDonChiTiet, Long> {
    List<HoaDonChiTiet> findByHoaDonId(Long hoaDonId);
    @Query("SELECT hdct.sanPhamChiTiet.sanPham.id, SUM(hdct.soLuong) " +
        "FROM HoaDonChiTiet hdct " +
        "WHERE hdct.hoaDon.trangThaiDonHang = :trangThaiHoanThanh " + // <-- ĐÚNG TRƯỜNG, ĐÚNG THAM SỐ
        "GROUP BY hdct.sanPhamChiTiet.sanPham.id " +
        "ORDER BY SUM(hdct.soLuong) DESC")
List<Object[]> findTopSellingProductIdsAndQuantities(@Param("trangThaiHoanThanh") TrangThaiDonHang trangThaiHoanThanh);}