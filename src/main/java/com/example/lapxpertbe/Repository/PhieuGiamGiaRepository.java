package com.example.lapxpertbe.Repository;

import com.example.lapxpertbe.Enity.PhieuGiamGia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;


@Repository
public interface PhieuGiamGiaRepository extends JpaRepository<PhieuGiamGia, Long> {

    @Query("SELECT pg FROM PhieuGiamGia pg " +
            "WHERE pg.trangThai = 'DA_DIEN_RA' " +
            "AND pg.ngayBatDau <= :currentTime " +
            "AND pg.ngayKetThuc >= :currentTime " +
            "AND pg.phieuRiengTu = false " +
            "ORDER BY pg.giaTriGiam DESC")
    List<PhieuGiamGia> findTopActivePublicVouchersByDiscountValue(@Param("currentTime") Instant currentTime);

    @Query("SELECT p FROM PhieuGiamGia p WHERE p.trangThai = 'DA_DIEN_RA' AND p.ngayBatDau <= CURRENT_TIMESTAMP AND p.ngayKetThuc >= CURRENT_TIMESTAMP " +
            "AND (p.giaTriDonHangToiThieu IS NULL OR p.giaTriDonHangToiThieu <= :tongTien) " +
            "AND (:nguoiDungId IS NULL OR EXISTS (SELECT 1 FROM PhieuGiamGiaNguoiDung pgng WHERE pgng.phieuGiamGia.id = p.id AND pgng.nguoiDung.id = :nguoiDungId AND pgng.daSuDung = FALSE))")
    List<PhieuGiamGia> findPhieuCoTheDung(@Param("tongTien") BigDecimal tongTien, @Param("nguoiDungId") Long nguoiDungId);

    @Query("SELECT p FROM PhieuGiamGia p WHERE p.trangThai = 'DA_DIEN_RA' AND p.ngayBatDau <= CURRENT_TIMESTAMP AND p.ngayKetThuc >= CURRENT_TIMESTAMP " +
            "AND (p.giaTriDonHangToiThieu IS NULL OR p.giaTriDonHangToiThieu <= :tongTien) " +
            "AND p.phieuRiengTu = false")
    List<PhieuGiamGia> findPhieuCongKhaiCoTheDung(@Param("tongTien") BigDecimal tongTien);
}