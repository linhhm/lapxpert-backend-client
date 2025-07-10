package com.example.lapxpertbe.DTO;

import lombok.Data;

@Data
public class LoginRequest {
    private String username; // email hoặc số điện thoại
    private String password;
}
