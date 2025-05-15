package com.example.lpiloguebe.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // auth
    USERNAME_NOT_FOUND(HttpStatus.NOT_FOUND, "USER-001", "사용자 정보가 없습니다."),
    USERNAME_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "USER-002", "이미 존재하는 ID 입니다."),
    BAD_PASSWORD(HttpStatus.BAD_REQUEST, "USER-003", "비밀번호가 일치하지 않습니다."),
    INVALID_USERNAME(HttpStatus.BAD_REQUEST, "USER-004", "아이디 형식이 올바르지 않습니다."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "USER-005", "비밀번호 형식이 올바르지 않습니다."),

    // user


    // song
    SONG_NOT_FOUND(HttpStatus.NOT_FOUND, "SONG-001", "해당 노래를 찾을 수 없습니다."),


    // diary
    DIARY_NOT_FOUND(HttpStatus.NOT_FOUND, "DIARY-001", "해당 다이어리를 찾을 수 없습니다."),
    INVALID_Date(HttpStatus.BAD_REQUEST, "DIARY-002", "일기 작성 날짜는 3일 이내여야 합니다.");



    private final HttpStatus status;
    private final String errorCode;
    private final String message;
}
