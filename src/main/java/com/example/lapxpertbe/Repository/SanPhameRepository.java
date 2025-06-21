package com.example.lapxpertbe.Repository;



import com.example.lapxpertbe.DTO.SanPhamDTO;
import com.example.lapxpertbe.Enity.SanPham;
import com.example.lapxpertbe.Enity.SanPhamChiTiet;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SanPhameRepository extends JpaRepository<SanPham, Long> {

    @EntityGraph(attributePaths = {"thuongHieu"})
    List<SanPham> findByTrangThai(Boolean trangThai);

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

}

