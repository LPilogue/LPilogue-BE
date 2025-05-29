package com.example.lpiloguebe.controller;

import com.example.lpiloguebe.apiPayload.code.ApiResponse;
import com.example.lpiloguebe.dto.UserRequestDTO;
import com.example.lpiloguebe.dto.UserResponseDTO;
import com.example.lpiloguebe.entity.User;
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
    public ApiResponse<UserResponseDTO.signupResultDTO> signup(@RequestBody UserRequestDTO.SignupDTO signupDTO) {
        User user = authService.signup(signupDTO);
        return ApiResponse.onSuccess(
                UserResponseDTO.signupResultDTO.builder()
                .userId(user.getId())
                .createdAt(user.getCreatedAt())
                .build());
    }

    @PostMapping("/signin")
    public ApiResponse<UserResponseDTO.signInResultDTO> signin(@RequestBody UserRequestDTO.SigninDTO signinDTO) {
        String jwtToken = authService.signin(signinDTO);

        return ApiResponse.onSuccess(
                UserResponseDTO.signInResultDTO.builder()
                        .accessToken("Bearer " + jwtToken)
                        .build());

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
