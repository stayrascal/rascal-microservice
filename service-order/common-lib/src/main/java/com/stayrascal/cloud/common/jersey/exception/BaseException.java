package com.stayrascal.cloud.common.jersey.exception;

import com.stayrascal.cloud.common.constant.ErrorCode;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

import javax.ws.rs.core.Response.Status;

public abstract class BaseException extends RuntimeException {
    private final Status status;
    private final FormattingTuple formatMessage;
    private final ErrorCode errorCode;

    protected BaseException(Status status, ErrorCode errorCode, String format, Object... params) {
        this(status, errorCode, MessageFormatter.arrayFormat(format, params));
    }

    protected BaseException(Status status, ErrorCode errorCode, String msg) {
        this(status, errorCode, MessageFormatter.arrayFormat(msg, (Object[]) null));
    }

    private BaseException(Status status, ErrorCode errorCode, FormattingTuple formatMessage) {
        super(formatMessage.getThrowable());
        this.status = status;
        this.errorCode = errorCode;
        this.formatMessage = formatMessage;
    }

    public Status getStatus() {
        return status;
    }

    public FormattingTuple getFormatMessage() {
        return formatMessage;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public RascalUnionExceptionResponse toUnionException() {
        return new RascalUnionExceptionResponse(this.errorCode, this.getMessage());
    }
}
