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
public class SanPhamChiTietDotGiamGiaId implements Serializable {
    private static final long serialVersionUID = 1211173578898683704L;
    @Column(name = "dot_giam_gia_id", nullable = false)
    private Long dotGiamGiaId;

    @Column(name = "san_pham_chi_tiet_id", nullable = false)
    private Long sanPhamChiTietId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SanPhamChiTietDotGiamGiaId entity = (SanPhamChiTietDotGiamGiaId) o;
        return Objects.equals(this.dotGiamGiaId, entity.dotGiamGiaId) &&
                Objects.equals(this.sanPhamChiTietId, entity.sanPhamChiTietId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dotGiamGiaId, sanPhamChiTietId);
    }

}