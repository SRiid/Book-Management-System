package com.example.BookManagementSpringBoot.security;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;

import com.example.BookManagementSpringBoot.model.User;
import com.example.BookManagementSpringBoot.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableMethodSecurity

@RequiredArgsConstructor
public class SecurityConfig {

	private final CustomUserDetailsService customUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // default strength = 10
    }
    @Bean
    public DaoAuthenticationProvider authProvider(PasswordEncoder encoder) {
        var provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customUserDetailsService);
        provider.setPasswordEncoder(encoder);
        return provider;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // APIs: typically disabled; use tokens if you need CSRF
            .httpBasic(Customizer.withDefaults()) // simple Basic auth
            .authorizeHttpRequests(auth -> auth
                // (Method security also guards, but we double-enforce via paths)
                .requestMatchers("/api/books/**").authenticated()
                .anyRequest().permitAll()
            );
        return http.build();
        
        
    }
    @Bean
    CommandLineRunner seedUsers(UserRepository repo, PasswordEncoder encoder) {
        return args -> {
            if (!repo.existsByUsername("admin")) {
                repo.save(User.builder()
                        .username("admin")
                        .password(encoder.encode("admin123"))
                        .role("ADMIN")
                        .build());
            }
            if (!repo.existsByUsername("user")) {
                repo.save(User.builder()
                        .username("user")
                        .password(encoder.encode("user123"))
                        .role("USER")
                        .build());
            }
        };

    }
}
