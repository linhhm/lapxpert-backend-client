package com.example.lapxpertbe.Repository;



import com.example.lapxpertbe.DTO.SanPhamDTO;
import com.example.lapxpertbe.Enity.SanPhamOnline;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SanPhamOnlineRepository extends JpaRepository<SanPhamOnline, Long> {

    @EntityGraph(attributePaths = {"thuongHieu"})
    List<SanPhamOnline> findByTrangThai(Boolean trangThai);

    @Query("""
    SELECT new com.example.lapxpertbe.DTO.SanPhamDTO(
        sp.id,
        sp.tenSanPham,
        sp.hinhAnh,
        th.tenThuongHieu,
        MIN(ct.giaBan),
        MAX(ct.giaBan)
    )
    FROM SanPhamOnline sp
    JOIN ChiTietSanPham ct ON ct.sanPham.id = sp.id
    JOIN ThuongHieu th ON th.id = sp.thuongHieu.id
    WHERE sp.trangThai = true
    GROUP BY sp.id, sp.tenSanPham, sp.hinhAnh, th.tenThuongHieu
""")
    List<SanPhamDTO> getSanPhamVaKhoangGia();

}

