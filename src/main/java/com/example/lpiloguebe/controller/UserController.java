package com.example.lpiloguebe.controller;

import com.example.lpiloguebe.dto.UserRequestDTO;
import com.example.lpiloguebe.service.UserService;
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
    public ResponseEntity<?> updateUser(@RequestBody UserRequestDTO.UserUpdateDTO userUpdateDTO) {
        userService.updateUser(userUpdateDTO);
        return new ResponseEntity<>("프로필 수정 완료", HttpStatus.OK);
    }
}
