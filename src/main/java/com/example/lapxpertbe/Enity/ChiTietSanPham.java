package com.example.lapxpertbe.Enity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "san_pham_chi_tiet")
public class ChiTietSanPham {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "gia_ban")
    private BigDecimal giaBan;



    @ManyToOne
    @JoinColumn(name = "san_pham_id") // chắc chắn tên cột đúng trong DB
    private SanPhamOnline sanPham;




}


