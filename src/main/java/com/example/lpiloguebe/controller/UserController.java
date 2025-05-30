package com.example.lpiloguebe.controller;

import com.example.lpiloguebe.apiPayload.code.ApiResponse;
import com.example.lpiloguebe.dto.UserRequestDTO;
import com.example.lpiloguebe.dto.UserResponseDTO;
import com.example.lpiloguebe.entity.User_prefer;
import com.example.lpiloguebe.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PutMapping("/")
    @Operation(summary = "프로필 수정", description = "사용자가 자신의 프로필을 수정할 수 있습니다.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "프로필 수정 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "잘못된 요청")
    })
    @Parameters({
            @io.swagger.v3.oas.annotations.Parameter(name = "happy", description = "기분이 좋을 때 더 듣고싶은 음악 1: 신나는 업템포 음악 0: 잔잔한 분위기의 음악", required = true),
            @io.swagger.v3.oas.annotations.Parameter(name = "sad", description = "우울할 때 찾게 되는 음악 1: 슬픔을 더 느낄 수 있는 감성적인 음악 0: 기분을 바꿔줄 밝은 음악", required = true),
            @io.swagger.v3.oas.annotations.Parameter(name = "stressed", description = "스트레스를 받을 때 더 끌리는 음악 1: 강렬한 비트와 강한 사운드의 음악 0: 차분하고 편안한 분위기의 음악", required = true),
            @io.swagger.v3.oas.annotations.Parameter(name = "lonely", description = "외로움을 느낄 때 더 듣고싶은 음악 1: 공감할 수 있는 가사와 감성적인 음악 0: 신나는 위로의 음악", required = true),
            @io.swagger.v3.oas.annotations.Parameter(name = "artist", description = "좋아하는 아티스트", required = true)
    })
    public ApiResponse<UserResponseDTO.userUpdateResultDTO> updateUser(@RequestBody UserRequestDTO.UserUpdateDTO userUpdateDTO) {
        User_prefer userPrefer = userService.updateUser(userUpdateDTO);
        return ApiResponse.onSuccess(
                UserResponseDTO.userUpdateResultDTO.builder()
                        .happy(userPrefer.getHappy())
                        .sad(userPrefer.getSad())
                        .stressed(userPrefer.getStressed())
                        .lonely(userPrefer.getLonely())
                        .artist(userPrefer.getArtist())
                        .build());
    }


}
