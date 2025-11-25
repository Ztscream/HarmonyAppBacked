package com.harmonyapp.backend.controller;

import com.harmonyapp.backend.common.ApiResponse;
import com.harmonyapp.backend.entity.User;
import com.harmonyapp.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/profile")
    public ApiResponse<Map<String, Object>> getProfile() {
        // In real app, get ID from token
        User user = userRepository.findById(1L).orElseThrow(() -> new RuntimeException("User not found"));

        Map<String, Object> data = new HashMap<>();
        data.put("userId", user.getId().toString());
        data.put("name", user.getName());
        data.put("phone", user.getPhone());
        data.put("level", user.getLevel());

        Map<String, Object> settings = new HashMap<>();
        settings.put("reminders", true);
        settings.put("privacy", "public");
        data.put("settings", settings);

        return ApiResponse.success(data);
    }
}
