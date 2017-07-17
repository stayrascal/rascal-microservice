package com.stayrascal.cloud.common.jersey.exception;

import com.stayrascal.cloud.common.lib.constant.ErrorCode;

public class RascalUnionExceptionResponse {
    private final ErrorCode errorCode;
    private final String message;

    public RascalUnionExceptionResponse(ErrorCode errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }
}
