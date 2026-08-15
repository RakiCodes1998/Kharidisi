package com.kharidisi.kharidisibackend.controller;

import com.kharidisi.kharidisibackend.dto.LoginRequest;
import com.kharidisi.kharidisibackend.dto.LoginResponse;
import com.kharidisi.kharidisibackend.entity.User;
import com.kharidisi.kharidisibackend.repository.UserRepository;
import com.kharidisi.kharidisibackend.security.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private  final  BCryptPasswordEncoder
    passwordEncoder = new BCryptPasswordEncoder();

    public AuthController(UserRepository userRepository,
                          JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(),user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        String token = jwtService.generateToken(user.getEmail(),user.getRole().name());

        return new LoginResponse(token);
    }
}
