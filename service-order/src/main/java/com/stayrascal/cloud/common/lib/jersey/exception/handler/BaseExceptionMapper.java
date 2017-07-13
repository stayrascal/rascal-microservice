package com.stayrascal.cloud.common.lib.jersey.exception.handler;

import com.stayrascal.cloud.common.lib.jersey.exception.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BaseExceptionMapper implements ExceptionMapper<BaseException> {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseException.class);

    public BaseExceptionMapper() {
    }

    public Response toResponse(BaseException exception) {
        LOGGER.warn("ResponseException of status: {}", exception.getStatus(), exception);
        return Response.status(exception.getStatus()).entity(exception.toUnionException()).type(MediaType.APPLICATION_JSON_TYPE).build();
    }
}
