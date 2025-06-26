package com.example.lapxpertbe.Repository;

import com.example.lapxpertbe.Enity.SerialNumberHoaDonChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SerialNumberHoaDonChiTietRepository extends JpaRepository<SerialNumberHoaDonChiTiet, Long> {
    List<SerialNumberHoaDonChiTiet> findByHoaDonChiTiet_Id(Long hoaDonChiTietId);
}