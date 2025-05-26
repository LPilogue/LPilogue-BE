package com.example.lpiloguebe.exception;

import com.example.lpiloguebe.apiPayload.code.ErrorCode;
import com.example.lpiloguebe.dto.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {
    private ErrorCode code;

    public ErrorReasonDTO getReason() {
        return this.code.getReason();
    }

    public ErrorReasonDTO getReasonHttpStatus() {
        return this.code.getReasonHttpStatus();
    }
}
