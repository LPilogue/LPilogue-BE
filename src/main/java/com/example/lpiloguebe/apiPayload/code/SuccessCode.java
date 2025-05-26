package com.example.lpiloguebe.apiPayload.code;

import com.example.lpiloguebe.dto.SuccessReasonDTO;

public interface SuccessCode {
    SuccessReasonDTO getReason();
    SuccessReasonDTO getReasonHttpStatus();
}
