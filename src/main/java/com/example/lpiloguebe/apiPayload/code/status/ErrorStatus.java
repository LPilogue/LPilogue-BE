package com.example.lpiloguebe.apiPayload.code.status;

import com.example.lpiloguebe.apiPayload.code.ErrorCode;
import com.example.lpiloguebe.dto.ErrorReasonDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorStatus implements ErrorCode {

    // 가장 일반적인 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),


    // auth
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER-001", "사용자 정보가 없습니다."),
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



    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build()
                ;
    }
}
