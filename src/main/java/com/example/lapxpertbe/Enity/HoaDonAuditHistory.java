package com.example.lapxpertbe.Enity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.util.Map;

@Getter
@Setter
@Entity
@Table(name = "hoa_don_audit_history")
public class HoaDonAuditHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "hoa_don_id", nullable = false)
    private Long hoaDonId;

    @Column(name = "hanh_dong", nullable = false, length = 50)
    private String hanhDong;

    @Column(name = "thoi_gian_thay_doi", nullable = false)
    private Instant thoiGianThayDoi;

    @Column(name = "nguoi_thuc_hien", length = 100)
    private String nguoiThucHien;

    @Column(name = "ly_do_thay_doi", length = 500)
    private String lyDoThayDoi;

    @Column(name = "gia_tri_cu")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> giaTriCu;

    @Column(name = "gia_tri_moi")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> giaTriMoi;

}