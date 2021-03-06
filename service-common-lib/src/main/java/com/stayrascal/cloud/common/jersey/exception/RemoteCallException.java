package com.stayrascal.cloud.common.jersey.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stayrascal.cloud.common.constant.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response.Status;
import java.util.Map;

public class RemoteCallException extends RuntimeException {
    private static final Logger LOGGER = LoggerFactory.getLogger(RemoteCallException.class);
    private final Status status;

    public RemoteCallException(Status status, String message) {
        super(message);
        this.status = status;
    }

    public Status getStatus() {
        return this.status;
    }

    public RascalUnionExceptionResponse toUnionException() {
        try {
            Map response = (Map) (new ObjectMapper()).readValue(this.getMessage(), Map.class);
            return new RascalUnionExceptionResponse(ErrorCode.valueOf(response.get("errorCode").toString()), response.get("message").toString());
        } catch (Exception var2) {
            LOGGER.info("try extract RemoteCallException failed", var2);
            return new RascalUnionExceptionResponse(ErrorCode.INTERNAL_ERROR, this.getMessage());
        }
    }
}
