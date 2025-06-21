package com.example.lapxpertbe.Enity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data

@Entity
@Table(name = "thuong_hieu")

public class ThuongHieu {

    @Id
    private Long id;

    @Column(name = "mo_ta_thuong_hieu")
    private String tenThuongHieu;

//    @OneToMany(mappedBy = "thuongHieu")
//    @JsonIgnoreProperties("thuongHieu") // 🛠️ TRÁNH đệ quy vô hạn
//    private List<SanPham> sanPhamOnlines;
}

