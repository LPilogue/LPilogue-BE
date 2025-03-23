package com.example.lpiloguebe.controller;

import com.example.lpiloguebe.dto.SignupDTO;
import com.example.lpiloguebe.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupDTO signupDTO) {

        if(signupDTO == null) {
            return new ResponseEntity<>("입력한 파라미터가 없습니다.", HttpStatus.BAD_REQUEST);
        }
        if(authService.signup(signupDTO) == null){
            return new ResponseEntity<>("회원가입 실패.", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("회원가입 완료.", HttpStatus.OK);
    }
}
