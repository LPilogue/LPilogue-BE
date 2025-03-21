package com.example.lpiloguebe.filter;

import com.example.lpiloguebe.dto.CustomUserDetails;
import com.example.lpiloguebe.entity.User;
import com.example.lpiloguebe.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");

        // 토큰이 없거나 Bearer로 시작하지 않으면 필터를 건너뜀
        if(authorization == null || !authorization.startsWith("Bearer")) {
            log.info("token null or not started with Bearer");
            filterChain.doFilter(request, response);
            return;
        }

        // Bearer로 시작하면 토큰을 추출
        String token = authorization.split(" ")[1];

        // 만료 여부 확인
        if(jwtUtil.isExpired(token)){
            filterChain.doFilter(request, response);
        }

        // 토큰이 유효함
        String username = jwtUtil.getUsername(token);

        User user = User.builder()
                .username(username)
                .build();

        CustomUserDetails customUserDetails = new CustomUserDetails(user);

        // Spring Security 인증 토큰 생성
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, null);

        // 세션에 user 등록
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);

    }
}
