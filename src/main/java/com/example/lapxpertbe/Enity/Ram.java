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
@Table(name = "ram")
public class Ram {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "ma_ram", nullable = false, length = 10)
    private String maRam;

    @Column(name = "mo_ta_ram", nullable = false, length = 100)
    private String moTaRam;

}