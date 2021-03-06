package com.stayrascal.cloud.common.jersey.exception;


import com.stayrascal.cloud.common.constant.ErrorCode;

import javax.ws.rs.core.Response;

public class BadRequestException extends BaseException {
    public BadRequestException(ErrorCode errorCode, String msg, Object... params) {
        super(Response.Status.BAD_REQUEST, errorCode, msg, params);
    }

    public BadRequestException(ErrorCode errorCode, String msg) {
        super(Response.Status.BAD_REQUEST, errorCode, msg);
    }
}
