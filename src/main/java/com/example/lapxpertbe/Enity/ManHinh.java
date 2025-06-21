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
@Table(name = "man_hinh")
public class ManHinh {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "ma_man_hinh", nullable = false, length = 10)
    private String maManHinh;

    @Column(name = "mo_ta_man_hinh", nullable = false, length = 300)
    private String moTaManHinh;

}