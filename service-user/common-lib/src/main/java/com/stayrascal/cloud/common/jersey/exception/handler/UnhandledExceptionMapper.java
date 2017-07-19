package com.stayrascal.cloud.common.jersey.exception.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class UnhandledExceptionMapper implements ExceptionMapper<RuntimeException> {
    private static final Logger LOGGER = LoggerFactory.getLogger(UnhandledExceptionMapper.class);

    public UnhandledExceptionMapper() {
    }

    public Response toResponse(RuntimeException exception) {
        LOGGER.warn("Unhandled exception", exception);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exception).type(MediaType.APPLICATION_JSON_TYPE).build();
    }
}
