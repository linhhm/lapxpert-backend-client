package com.example.lapxpertbe.Repository;

import com.example.lapxpertbe.Enity.PhieuGiamGia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PhieuGiamGiaRepository extends JpaRepository<PhieuGiamGia, Long> {
    // Các truy vấn khác
    @Query("SELECT p FROM PhieuGiamGia p WHERE p.trangThai = 'DA_DIEN_RA' AND p.ngayBatDau <= CURRENT_TIMESTAMP AND p.ngayKetThuc >= CURRENT_TIMESTAMP " +
            "AND (p.giaTriDonHangToiThieu IS NULL OR p.giaTriDonHangToiThieu <= :tongTien) " +
            "AND (:nguoiDungId IS NULL OR EXISTS (SELECT 1 FROM PhieuGiamGiaNguoiDung pgng WHERE pgng.phieuGiamGia.id = p.id AND pgng.nguoiDung.id = :nguoiDungId AND pgng.daSuDung = FALSE))")
    List<PhieuGiamGia> findPhieuCoTheDung(@Param("tongTien") BigDecimal tongTien, @Param("nguoiDungId") Long nguoiDungId);

    // Truy vấn phiếu giảm giá công khai (không cần người dùng)
    @Query("SELECT p FROM PhieuGiamGia p WHERE p.trangThai = 'DA_DIEN_RA' AND p.ngayBatDau <= CURRENT_TIMESTAMP AND p.ngayKetThuc >= CURRENT_TIMESTAMP " +
            "AND (p.giaTriDonHangToiThieu IS NULL OR p.giaTriDonHangToiThieu <= :tongTien) " +
            "AND p.phieuRiengTu = false")
    List<PhieuGiamGia> findPhieuCongKhaiCoTheDung(@Param("tongTien") BigDecimal tongTien);
}