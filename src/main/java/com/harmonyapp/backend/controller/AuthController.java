package com.harmonyapp.backend.controller;

import com.harmonyapp.backend.common.ApiResponse;
import com.harmonyapp.backend.dto.LoginRequest;
import com.harmonyapp.backend.dto.RegisterRequest;
import com.harmonyapp.backend.entity.User;
import com.harmonyapp.backend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ApiResponse<Map<String, Object>> register(@RequestBody RegisterRequest request) {
        User user = authService.register(request.getName(), request.getPhone(), request.getPassword());
        Map<String, Object> data = new HashMap<>();
        data.put("userId", user.getId().toString()); // Frontend expects String ID
        return ApiResponse.success("注册成功", data);
    }

    @PostMapping("/login")
    public ApiResponse<Map<String, Object>> login(@RequestBody LoginRequest request) {
        User user = authService.login(request.getPhone(), request.getPassword());
        String token = authService.generateToken(user);

        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("userId", user.getId().toString());
        userInfo.put("name", user.getName());
        userInfo.put("phone", user.getPhone());

        Map<String, Object> data = new HashMap<>();
        data.put("token", token);
        data.put("userInfo", userInfo);

        return ApiResponse.success("登录成功", data);
    }
}
