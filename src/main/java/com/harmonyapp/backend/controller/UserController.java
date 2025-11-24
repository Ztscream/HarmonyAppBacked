package com.harmonyapp.backend.controller;

import com.harmonyapp.backend.entity.User;
import com.harmonyapp.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/profile")
    public User getProfile() {
        // In real app, get ID from token
        return userRepository.findById(1L).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
