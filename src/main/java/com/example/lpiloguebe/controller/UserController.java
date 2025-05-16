package com.example.lpiloguebe.controller;

import com.example.lpiloguebe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PatchMapping("/")
    public ResponseEntity<?> updateUser() {
        userService.updateUser();
        return new ResponseEntity<>("프로필 수정 완료", HttpStatus.OK);
    }
}
