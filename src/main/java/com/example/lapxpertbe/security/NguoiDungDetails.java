package com.example.lapxpertbe.security;

import com.example.lapxpertbe.Enity.NguoiDung;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class NguoiDungDetails implements UserDetails {
    private final NguoiDung nguoiDung;

    public NguoiDungDetails(NguoiDung nguoiDung) {
        this.nguoiDung = nguoiDung;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList(); // Hoặc phân quyền nếu bạn dùng vai trò
    }

    @Override
    public String getPassword() {
        return nguoiDung.getMatKhau();
    }

    @Override
    public String getUsername() {
        return nguoiDung.getEmail() != null ? nguoiDung.getEmail() : nguoiDung.getSoDienThoai();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return nguoiDung.getTrangThai().equals("ACTIVE");
    }

    public NguoiDung getNguoiDung() {
        return nguoiDung;
    }
}
