// Trong com.example.lapxpertbe.Repository.SanPhameRepository.java
package com.example.lapxpertbe.Repository;

import com.example.lapxpertbe.DTO.SanPhamBanChayDTO;
import com.example.lapxpertbe.DTO.SanPhamDTO;
import com.example.lapxpertbe.Enity.SanPham;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.domain.Pageable; // Cần import này
import com.example.lapxpertbe.enums.TrangThaiDonHang; // Cần import này nếu dùng trong query

@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, Long> {

    @EntityGraph(attributePaths = {"thuongHieu", "danhMucs"})
    List<SanPham> findByTrangThai(Boolean trangThai);


    @EntityGraph(attributePaths = {"thuongHieu", "chiTietSanPhams", "danhMucs"})
    List<SanPham> findByDanhMucs_Id(Long danhMucId);

    @Query("""
    SELECT new com.example.lapxpertbe.DTO.SanPhamDTO(
        sp.id,
        sp.tenSanPham,
        sp.hinhAnh,
        th.tenThuongHieu,
        MIN(ct.giaBan),
        MAX(ct.giaBan)
    )
    FROM SanPham sp
    JOIN SanPhamChiTiet ct ON ct.sanPham.id = sp.id
    JOIN ThuongHieu th ON th.id = sp.thuongHieu.id
    WHERE sp.trangThai = true
    GROUP BY sp.id, sp.tenSanPham, sp.hinhAnh, th.tenThuongHieu
""")
    List<SanPhamDTO> getSanPhamVaKhoangGia();


    @Query("""
SELECT new com.example.lapxpertbe.DTO.SanPhamBanChayDTO(
    sp.id,
    sp.maSanPham,            
    sp.tenSanPham,
    sp.hinhAnh,               
    sp.thuongHieu.tenThuongHieu,
    SUM(cthd.soLuong),     
    MIN(spct.giaBan),        
    MIN(spct.giaKhuyenMai)    
)
FROM SanPham sp
JOIN SanPhamChiTiet spct ON sp.id = spct.sanPham.id
JOIN HoaDonChiTiet cthd ON spct.id = cthd.sanPhamChiTiet.id
JOIN HoaDon hd ON cthd.hoaDon.id = hd.id
WHERE hd.trangThaiDonHang = :trangThaiHoanThanh AND sp.trangThai = true
GROUP BY
    sp.id,
    sp.maSanPham,           
    sp.tenSanPham,
    sp.hinhAnh,
    sp.thuongHieu.tenThuongHieu 
ORDER BY SUM(cthd.soLuong) DESC
""")
    List<SanPhamBanChayDTO> findTopSellingProducts( @Param("trangThaiHoanThanh") TrangThaiDonHang trangThaiHoanThanh, Pageable pageable);
}