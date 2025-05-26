package com.example.lpiloguebe.controller;

import com.example.lpiloguebe.dto.SigninDTO;
import com.example.lpiloguebe.dto.SignupDTO;
import com.example.lpiloguebe.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupDTO signupDTO) {
        authService.signup(signupDTO);
        return new ResponseEntity<>("회원가입 완료", HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody SigninDTO signinDTO) {
        String jwtToken = authService.signin(signinDTO);

        // JWT 토큰을 HTTP 응답 헤더에 추가
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + jwtToken);

        return ResponseEntity.ok()
                .headers(headers)
                .body("로그인 성공");
    }

    @GetMapping("/exists-username")
    public ResponseEntity<?> checkUsername(@RequestParam String username) {
        authService.checkUsername(username);
        return new ResponseEntity<>("사용 가능한 ID 입니다.", HttpStatus.OK);

    }

    @GetMapping("/check-username-password")
    public ResponseEntity<?> checkUsernamePassword(@RequestParam String username, @RequestParam String password) {
        authService.checkUsernamePassword(username, password);
        return new ResponseEntity<>("사용 가능한 ID & 비밀번호 입니다.", HttpStatus.OK);
    }
}
