package com.example.lapxpertbe.Enity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.util.Map;

@Getter
@Setter
@Entity
@Table(name = "serial_number_audit_history")
public class SerialNumberAuditHistory {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "serial_number_id", nullable = false)
    private Long serialNumberId;

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

    @Column(name = "ip_address", length = 45)
    private String ipAddress;

    @Column(name = "user_agent", length = 500)
    private String userAgent;

    @Column(name = "batch_operation_id", length = 50)
    private String batchOperationId;

    @Column(name = "order_id", length = 50)
    private String orderId;

    @Column(name = "channel", length = 20)
    private String channel;

    @Column(name = "metadata")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> metadata;

}