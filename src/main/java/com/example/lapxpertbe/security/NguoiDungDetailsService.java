package com.example.lapxpertbe.security;

import com.example.lapxpertbe.Enity.NguoiDung;
import com.example.lapxpertbe.Repository.NguoiDungRepository;
import com.example.lapxpertbe.enums.VaiTroNguoiDung;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NguoiDungDetailsService implements UserDetailsService {
    private final NguoiDungRepository nguoiDungRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        NguoiDung nguoiDung = nguoiDungRepo
                .findByEmailOrSoDienThoai(username, username)
                .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy người dùng"));

        // ✅ Chỉ cho phép vai trò CUSTOMER đăng nhập
        if (nguoiDung.getVaiTro() != VaiTroNguoiDung.CUSTOMER) {
            throw new UsernameNotFoundException("Bạn không có quyền đăng nhập ở trang khách hàng.");
        }

        return new NguoiDungDetails(nguoiDung);
    }

}
