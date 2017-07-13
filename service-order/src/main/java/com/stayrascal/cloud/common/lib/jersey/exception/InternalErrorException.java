package com.stayrascal.cloud.common.lib.jersey.exception;

import com.stayrascal.cloud.common.lib.constant.ErrorCode;

import javax.ws.rs.core.Response;

public class InternalErrorException extends BaseException {
    public InternalErrorException(Response.Status status, ErrorCode errorCode, String format, Object... params) {
        super(status, errorCode, format, params);
    }

    public InternalErrorException(ErrorCode errorCode, String msg, Object... params) {
        super(Response.Status.FORBIDDEN, errorCode, msg, params);
    }
}
