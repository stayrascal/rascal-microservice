package com.stayrascal.cloud.common.lib.jersey.exception.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class WebApplicationExceptionMapper implements ExceptionMapper<WebApplicationException> {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebApplicationExceptionMapper.class);

    public WebApplicationExceptionMapper() {
    }

    public Response toResponse(WebApplicationException exception) {
        LOGGER.warn("Unhandled WebApplicationException, with response {}", exception.getResponse(), exception);
        return exception.getResponse() != null ? exception.getResponse() : Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exception).type(MediaType.APPLICATION_JSON_TYPE).build();
    }
}
