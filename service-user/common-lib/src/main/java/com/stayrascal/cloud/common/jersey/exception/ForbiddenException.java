package com.stayrascal.cloud.common.jersey.exception;


import com.stayrascal.cloud.common.constant.ErrorCode;

import javax.ws.rs.core.Response;

public class ForbiddenException extends BaseException {
    public ForbiddenException(Response.Status status, ErrorCode errorCode, String format, Object... params) {
        super(status, errorCode, format, params);
    }

    public ForbiddenException(ErrorCode errorCode, String msg, Object... params) {
        super(Response.Status.FORBIDDEN, errorCode, msg, params);
    }
}
