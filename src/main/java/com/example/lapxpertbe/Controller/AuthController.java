package com.example.lapxpertbe.Controller;

import com.example.lapxpertbe.DTO.LoginRequest;
import com.example.lapxpertbe.security.NguoiDungDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authManager;
    private final NguoiDungDetailsService userDetailsService;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        // Nếu bạn dùng JWT, ở đây sẽ tạo token
        UserDetails user = (UserDetails) authentication.getPrincipal();
        return "Đăng nhập thành công với " + user.getUsername();
    }
}

