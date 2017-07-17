package com.stayrascal.cloud.common.jersey.exception;

import com.stayrascal.cloud.common.lib.constant.ErrorCode;

import javax.ws.rs.core.Response.Status;

public class UnauthorizedException extends BaseException {
    public UnauthorizedException(ErrorCode errorCode, String msg) {
        super(Status.UNAUTHORIZED, errorCode, msg);
    }

    public UnauthorizedException(ErrorCode errorCode, String msg, Object... params) {
        super(Status.UNAUTHORIZED, errorCode, msg, params);
    }
}
