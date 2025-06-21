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
@Table(name = "gpu")
public class Gpu {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "ma_gpu", nullable = false, length = 10)
    private String maGpu;

    @Column(name = "mo_ta_gpu", nullable = false)
    private String moTaGpu;

}