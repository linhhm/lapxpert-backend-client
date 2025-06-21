package com.example.lapxpertbe.Repository;

import com.example.lapxpertbe.Enity.SerialNumber;
import com.example.lapxpertbe.enums.TrangThaiSerialNumber;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface SerialNumberRepository extends JpaRepository<SerialNumber, Long> {
    long countBySanPhamChiTietIdAndTrangThai(Long sanPhamChiTietId, TrangThaiSerialNumber trangThai);

    @Query(value = "SELECT * FROM serial_number WHERE san_pham_chi_tiet_id = :spctId AND trang_thai = :trangThai LIMIT :limit", nativeQuery = true)
    List<SerialNumber> findTopNBySanPhamChiTietIdAndTrangThai(Long spctId, String trangThai, int limit);

}