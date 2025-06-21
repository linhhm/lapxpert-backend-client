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
@Table(name = "bo_nho")
public class BoNho {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "ma_bo_nho", nullable = false, length = 10)
    private String maBoNho;

    @Column(name = "mo_ta_bo_nho", nullable = false, length = 150)
    private String moTaBoNho;

}