package com.stayrascal.cloud.common.jersey.exception;


import com.stayrascal.cloud.common.constant.ErrorCode;

import javax.ws.rs.core.Response;

public class ServerErrorException extends BaseException {
    public ServerErrorException(Response.Status status, ErrorCode errorCode, String format, Object... params) {
        super(status, errorCode, format, params);
    }

    public ServerErrorException(ErrorCode errorCode, String format, Object... params) {
        super(Response.Status.INTERNAL_SERVER_ERROR, errorCode, format, params);
    }
}
