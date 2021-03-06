package com.stayrascal.cloud.common.jersey.exception;


import com.stayrascal.cloud.common.constant.ErrorCode;

import javax.ws.rs.core.Response;

public class NotFoundException extends BaseException {
    public NotFoundException(Response.Status status, ErrorCode errorCode, String format, Object... params) {
        super(status, errorCode, format, params);
    }

    public NotFoundException(ErrorCode errorCode, String msg, Object... params) {
        super(Response.Status.NOT_FOUND, errorCode, msg, params);
    }
}
