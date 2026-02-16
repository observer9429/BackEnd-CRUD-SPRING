package com.security.basico.basic.controllers;

import jakarta.validation.Valid;

import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.security.basico.basic.dto.LoginRequestDto;
import com.security.basico.basic.dto.LoginResponseDto;
import com.security.basico.basic.security.jwt.JwtUtil;
import com.security.basico.basic.security.service.CustomUserDetailsService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager,
                          CustomUserDetailsService userDetailsService,
                          JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public LoginResponseDto login(@Valid @RequestBody LoginRequestDto dto) {

        // 🔐 valida credenciales (usa BCrypt internamente)
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getUsername(),
                        dto.getPassword()
                )
        );

        UserDetails userDetails =
                userDetailsService.loadUserByUsername(dto.getUsername());

        String token = jwtUtil.generateToken(userDetails);

        return new LoginResponseDto(token);
    }
}
