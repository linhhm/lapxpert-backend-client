package com.example.lapxpertbe.Enity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SanPhamDanhMucId implements Serializable {
    @Column(name = "san_pham_id")
    private Long sanPhamId;

    @Column(name = "danh_muc_id")
    private Long danhMucId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SanPhamDanhMucId that = (SanPhamDanhMucId) o;
        return Objects.equals(sanPhamId, that.sanPhamId) &&
                Objects.equals(danhMucId, that.danhMucId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sanPhamId, danhMucId);
    }
}
