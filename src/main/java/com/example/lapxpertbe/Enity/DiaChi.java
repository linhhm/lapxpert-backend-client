package com.example.lapxpertbe.Enity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "dia_chi")
public class DiaChi {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "duong", nullable = false)
    private String duong;

    @Column(name = "phuong_xa", nullable = false, length = 100)
    private String phuongXa;

    @Column(name = "quan_huyen", nullable = false, length = 100)
    private String quanHuyen;

    @Column(name = "tinh_thanh", nullable = false, length = 100)
    private String tinhThanh;

    @Column(name = "quoc_gia", length = 100)
    private String quocGia;

    @Column(name = "loai_dia_chi", length = 50)
    private String loaiDiaChi;

    @Column(name = "la_mac_dinh", nullable = false)
    private Boolean laMacDinh = false;

    @Column(name = "ngay_tao")
    private OffsetDateTime ngayTao;

    @Column(name = "ngay_cap_nhat")
    private OffsetDateTime ngayCapNhat;

}