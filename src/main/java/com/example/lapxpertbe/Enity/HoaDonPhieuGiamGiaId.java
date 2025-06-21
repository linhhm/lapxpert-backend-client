package com.example.lapxpertbe.Enity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class HoaDonPhieuGiamGiaId implements Serializable {
    private static final long serialVersionUID = 5552686616941818651L;
    @Column(name = "hoa_don_id", nullable = false)
    private Long hoaDonId;

    @Column(name = "phieu_giam_gia_id", nullable = false)
    private Long phieuGiamGiaId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        HoaDonPhieuGiamGiaId entity = (HoaDonPhieuGiamGiaId) o;
        return Objects.equals(this.hoaDonId, entity.hoaDonId) &&
                Objects.equals(this.phieuGiamGiaId, entity.phieuGiamGiaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hoaDonId, phieuGiamGiaId);
    }

}