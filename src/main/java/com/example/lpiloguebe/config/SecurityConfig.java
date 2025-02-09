package com.example.lpiloguebe.config;

import com.example.lpiloguebe.filter.LoginFilter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {


    // 비밀번호 암호화할 때 필요
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private final AuthenticationConfiguration authenticationConfiguration;

    // AuthenticationManager 주입 위한 빈 등록
    @Bean
    public AuthenticationManager getAuthenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {

        // 폼 로그인 비활성화
        http
                .formLogin(AbstractHttpConfigurer::disable);
        http
                .httpBasic(AbstractHttpConfigurer::disable);

        // csrf 비활성화
        http
                .csrf(AbstractHttpConfigurer::disable);

        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/", "/api/auth/signin", "/api/auth/signup").permitAll()
                        .anyRequest().authenticated()
                );

        // 세션 stateless 설정
        http
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        // 필터 추가
        http
                .addFilterAt(new LoginFilter(getAuthenticationManager(authenticationConfiguration)), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
