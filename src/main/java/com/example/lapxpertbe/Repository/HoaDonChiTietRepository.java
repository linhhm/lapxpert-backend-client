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
    @Query("SELECT cthd.sanPhamChiTiet.sanPham.id, SUM(cthd.soLuong) " +
            "FROM HoaDonChiTiet cthd " +
            "JOIN cthd.hoaDon hd " +
            "WHERE hd.trangThaiDonHang = :trangThaiHoanThanh " +
            "GROUP BY cthd.sanPhamChiTiet.sanPham.id")
    List<Object[]> findTopSellingProductIdsAndQuantities(@Param("trangThaiHoanThanh") TrangThaiDonHang trangThaiHoanThanh);

    List<HoaDonChiTiet> findByHoaDonId(Long hoaDonId);
}