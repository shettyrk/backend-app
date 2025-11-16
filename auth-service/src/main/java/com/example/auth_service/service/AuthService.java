package com.example.auth_service.service;


import com.example.auth_service.dto.AuthRequest;
import com.example.auth_service.dto.AuthResponse;
import com.example.auth_service.dto.SignupRequest;
import com.example.auth_service.model.User;
import com.example.auth_service.repo.UserRepository;
import com.example.auth_service.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public void signup(SignupRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("User already exists");
        }
        // default role USER
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles("USER")
                .build();
        userRepository.save(user);
    }

    public AuthResponse login(AuthRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        List<String> roles = Arrays.stream(user.getRoles().split(","))
                .map(String::trim)
                .toList();

        String token = jwtUtils.generateToken(user.getEmail(), roles);
        return new AuthResponse(token);
    }

    // admin helper: promote a user to ADMIN
    public void promoteToAdmin(String email) {
        User u = userRepository.findByEmail(email).orElseThrow();
        if (!u.getRoles().contains("ADMIN")) {
            u.setRoles(u.getRoles() + ",ADMIN");
            userRepository.save(u);
        }
    }
}

