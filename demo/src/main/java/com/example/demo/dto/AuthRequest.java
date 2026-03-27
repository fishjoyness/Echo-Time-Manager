package com.example.demo.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String username; // 接收前端发来的账号
    private String password; // 接收前端发来的密码
}