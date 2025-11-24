package com.harmonyapp.backend.controller;

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
    public Map<String, Object> register(@RequestBody Map<String, String> body) {
        User user = authService.register(body.get("name"), body.get("phone"), body.get("password"));
        String token = authService.generateToken(user);
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("user", user);
        return response;
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> body) {
        User user = authService.login(body.get("phone"), body.get("password"));
        String token = authService.generateToken(user);
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("user", user);
        return response;
    }
}
