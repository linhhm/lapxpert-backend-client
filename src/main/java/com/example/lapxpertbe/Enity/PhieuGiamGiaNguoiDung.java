package com.example.lapxpertbe.Enity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "phieu_giam_gia_nguoi_dung")
public class PhieuGiamGiaNguoiDung {
    @EmbeddedId
    private PhieuGiamGiaNguoiDungId id;

    @MapsId("phieuGiamGiaId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "phieu_giam_gia_id", nullable = false)
    private PhieuGiamGia phieuGiamGia;

    @MapsId("nguoiDungId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "nguoi_dung_id", nullable = false)
    private NguoiDung nguoiDung;

    @Column(name = "ngay_nhan")
    private Instant ngayNhan;

    @Column(name = "da_su_dung", nullable = false)
    private Boolean daSuDung = false;

    @Column(name = "ngay_su_dung")
    private Instant ngaySuDung;

    @Column(name = "ngay_tao")
    private Instant ngayTao;

    @Column(name = "ngay_cap_nhat")
    private Instant ngayCapNhat;

}