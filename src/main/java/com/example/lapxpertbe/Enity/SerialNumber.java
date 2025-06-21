package com.example.lapxpertbe.Enity;

import com.example.lapxpertbe.enums.TrangThaiSerialNumber;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "serial_number")
public class SerialNumber {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "ngay_tao", nullable = false)
    private Instant ngayTao;

    @Column(name = "ngay_cap_nhat", nullable = false)
    private Instant ngayCapNhat;

    @Column(name = "nguoi_tao", length = 100)
    private String nguoiTao;

    @Column(name = "nguoi_cap_nhat", length = 100)
    private String nguoiCapNhat;

    @Column(name = "serial_number_value", nullable = false, length = 100)
    private String serialNumberValue;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "san_pham_chi_tiet_id", nullable = false)
    private SanPhamChiTiet sanPhamChiTiet;

    @Enumerated(EnumType.STRING)
    @Column(name = "trang_thai", nullable = false)
    private TrangThaiSerialNumber trangThai;

    @Column(name = "thoi_gian_dat_truoc")
    private Instant thoiGianDatTruoc;

    @Column(name = "kenh_dat_truoc", length = 20)
    private String kenhDatTruoc;

    @Column(name = "don_hang_dat_truoc", length = 50)
    private String donHangDatTruoc;

    @Column(name = "batch_number", length = 50)
    private String batchNumber;

    @Column(name = "ngay_san_xuat")
    private Instant ngaySanXuat;

    @Column(name = "ngay_het_bao_hanh")
    private Instant ngayHetBaoHanh;

    @Column(name = "nha_cung_cap", length = 100)
    private String nhaCungCap;

    @Column(name = "import_batch_id", length = 50)
    private String importBatchId;

    @Column(name = "ghi_chu", length = 500)
    private String ghiChu;

}