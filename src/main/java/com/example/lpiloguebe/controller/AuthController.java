package com.example.lpiloguebe.controller;

import com.example.lpiloguebe.apiPayload.code.ApiResponse;
import com.example.lpiloguebe.dto.UserRequestDTO;
import com.example.lpiloguebe.dto.UserResponseDTO;
import com.example.lpiloguebe.entity.User;
import com.example.lpiloguebe.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;


    @PostMapping("/signup")
    @Operation(summary = "회원가입", description = "사용자가 회원가입을 할 수 있습니다.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "회원가입 성공"),
    })
    @Parameters({
            @io.swagger.v3.oas.annotations.Parameter(name = "username", description = "로그인 ID"),
            @io.swagger.v3.oas.annotations.Parameter(name = "password", description = "로그인 비밀번호"),
            @io.swagger.v3.oas.annotations.Parameter(name = "nickname", description = "사용자 닉네임" ),
            @io.swagger.v3.oas.annotations.Parameter(name = "city", description = "사용자 거주 도시"),
            @io.swagger.v3.oas.annotations.Parameter(name = "happy", description = "기분이 좋을 때 더 듣고싶은 음악 1: 신나는 업템포 음악 0: 잔잔한 분위기의 음악"),
            @io.swagger.v3.oas.annotations.Parameter(name = "sad", description = "우울할 때 찾게 되는 음악 1: 슬픔을 더 느낄 수 있는 감성적인 음악 0: 기분을 바꿔줄 밝은 음악"),
            @io.swagger.v3.oas.annotations.Parameter(name = "stressed", description = "스트레스를 받을 때 더 끌리는 음악 1: 강렬한 비트와 강한 사운드의 음악 0: 차분하고 편안한 분위기의 음악"),
            @io.swagger.v3.oas.annotations.Parameter(name = "lonely", description = "외로움을 느낄 때 더 듣고싶은 음악 1: 공감할 수 있는 가사와 감성적인 음악 0: 신나는 위로의 음악"),
            @io.swagger.v3.oas.annotations.Parameter(name = "artist", description = "좋아하는 아티스트")
    })
    public ApiResponse<UserResponseDTO.signupResultDTO> signup(@RequestBody UserRequestDTO.SignupDTO signupDTO) {
        User user = authService.signup(signupDTO);
        return ApiResponse.onSuccess(
                UserResponseDTO.signupResultDTO.builder()
                .userId(user.getId())
                .createdAt(user.getCreatedAt())
                .build());
    }

    @PostMapping("/signin")
    @Operation(summary = "로그인", description = "사용자가 로그인할 수 있습니다. 1시간 동안 유효한 JWT 토큰을 반환합니다.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "로그인 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "로그인 실패")
    })
    @Parameters({
            @io.swagger.v3.oas.annotations.Parameter(name = "username", description = "로그인 ID"),
            @io.swagger.v3.oas.annotations.Parameter(name = "password", description = "로그인 비밀번호")
    })
    public ApiResponse<UserResponseDTO.signInResultDTO> signin(@RequestBody UserRequestDTO.SigninDTO signinDTO) {
        UserResponseDTO.signInResultDTO signin = authService.signin(signinDTO);

        return ApiResponse.onSuccess(signin);

    }

    @GetMapping("/exists-username")
    @Operation(summary = "사용자 ID 중복 확인", description = "사용자가 입력한 ID가 이미 존재하는지 확인합니다.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "사용 가능한 ID"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "이미 존재하는 ID")
    })
    @Parameters({
            @io.swagger.v3.oas.annotations.Parameter(name = "username", description = "사용자 ID", required = true)
    })
    public ApiResponse<UserResponseDTO.checkUsernameResultDTO> checkUsername(@RequestParam String username) {
        authService.checkUsername(username);
        return ApiResponse.onSuccess(
                UserResponseDTO.checkUsernameResultDTO.builder()
                        .username(username)
                        .isExists(false)
                        .message("사용 가능한 ID 입니다.")
                        .build());

    }

    @GetMapping("/check-username-password")
    @Operation(summary = "사용자 ID와 비밀번호 확인", description = "사용자가 입력한 ID와 비밀번호가 조건에 맞는지 확인합니다.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "사용 가능한 ID와 비밀번호"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "사용 불가능한 ID와 비밀번호")
    })
    @Parameters({
            @io.swagger.v3.oas.annotations.Parameter(name = "username", description = "사용자 ID", required = true),
            @io.swagger.v3.oas.annotations.Parameter(name = "password", description = "사용자 비밀번호", required = true)
    })
    public ApiResponse<UserResponseDTO.checkUsernamePasswordResultDTO> checkUsernamePassword(@RequestParam String username, @RequestParam String password) {
        authService.checkUsernamePassword(username, password);
        return ApiResponse.onSuccess(
                UserResponseDTO.checkUsernamePasswordResultDTO.builder()
                        .username(username)
                        .password(password)
                        .message("사용 가능한 ID와 비밀번호 입니다.")
                        .build());
    }
}
