package com.stayrascal.cloud.common.jersey.exception.handler;

import com.stayrascal.cloud.common.jersey.exception.RemoteCallException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RemoteCallExceptionMapper implements ExceptionMapper<RemoteCallException> {
    private static final Logger LOGGER = LoggerFactory.getLogger(RemoteCallExceptionMapper.class);

    public RemoteCallExceptionMapper() {
    }

    public Response toResponse(RemoteCallException exception) {
        LOGGER.warn("Unhandled remote call exception", exception);
        return Response.status(exception.getStatus()).entity(exception.toUnionException()).type(MediaType.APPLICATION_JSON_TYPE).build();
    }
}
