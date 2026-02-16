package com.security.basico.basic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityBeansConfig {

    //INDICA Quiero un solo PasswordEncoder en toda la app
    // Define el PasswordEncoder global del proyecto.
// Se usa para:
// 1. Cifrar passwords al crear usuarios
// 2. Comparar passwords en el login (BCrypt)
// Spring Security lo usa automáticamente.

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

