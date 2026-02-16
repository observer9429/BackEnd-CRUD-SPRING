package com.security.basico.basic.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.security.basico.basic.security.jwt.JwtFilter;

@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            // API REST → no CSRF
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth
                // login público
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers("/api/public/products/**").permitAll()

                // SOLO ADMIN
                .requestMatchers("/api/admin/**").hasRole("ADMIN")

                // todo lo demás requiere token válido
                .anyRequest().authenticated()
            )

            // 🔥 aquí entra el JWT antes del login clásico
            .addFilterBefore(jwtFilter,
                    UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // necesario para AuthController (login)
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
