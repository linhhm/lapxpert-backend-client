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
@Table(name = "cpu")
public class Cpu {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "ma_cpu", nullable = false, length = 10)
    private String maCpu;

    @Column(name = "mo_ta_cpu", nullable = false)
    private String moTaCpu;

}