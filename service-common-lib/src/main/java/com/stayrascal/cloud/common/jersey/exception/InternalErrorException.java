package com.stayrascal.cloud.common.jersey.exception;


import com.stayrascal.cloud.common.constant.ErrorCode;

import javax.ws.rs.core.Response;

public class InternalErrorException extends BaseException {
    public InternalErrorException(Response.Status status, ErrorCode errorCode, String format, Object... params) {
        super(status, errorCode, format, params);
    }

    public InternalErrorException(ErrorCode errorCode, String msg, Object... params) {
        super(Response.Status.INTERNAL_SERVER_ERROR, errorCode, msg, params);
    }
}
