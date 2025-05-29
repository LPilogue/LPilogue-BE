package com.example.lpiloguebe.apiPayload.code;

import com.example.lpiloguebe.dto.ErrorReasonDTO;

public interface ErrorCode {
    ErrorReasonDTO getReason();
    ErrorReasonDTO getReasonHttpStatus();
}
