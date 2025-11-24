package com.harmonyapp.backend.service;

import com.harmonyapp.backend.entity.User;
import com.harmonyapp.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public User register(String name, String phone, String password) {
        if (userRepository.findByPhone(phone).isPresent()) {
            throw new RuntimeException("User already exists");
        }
        User user = new User();
        user.setName(name);
        user.setPhone(phone);
        user.setPassword(password); // In production, hash this!
        return userRepository.save(user);
    }

    public User login(String phone, String password) {
        Optional<User> userOpt = userRepository.findByPhone(phone);
        if (userOpt.isPresent() && userOpt.get().getPassword().equals(password)) {
            return userOpt.get();
        }
        throw new RuntimeException("Invalid credentials");
    }

    public String generateToken(User user) {
        return UUID.randomUUID().toString(); // Simple dummy token
    }
}
