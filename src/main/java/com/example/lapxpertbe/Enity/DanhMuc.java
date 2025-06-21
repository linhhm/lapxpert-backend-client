package com.example.lapxpertbe.Enity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "danh_muc")
public class DanhMuc {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "ma_danh_muc", nullable = false, length = 10)
    private String maDanhMuc;

    @Column(name = "mo_ta_danh_muc", nullable = false)
    private String moTaDanhMuc;

}