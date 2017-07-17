package com.stayrascal.cloud.common.jersey.exception.handler;

import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class FeignExceptionMapper implements ExceptionMapper<FeignException> {

    private static final Logger LOGGER = LoggerFactory.getLogger(FeignExceptionMapper.class);

    public FeignExceptionMapper() {
    }

    public Response toResponse(FeignException exception) {
        FeignException feignException = (FeignException)FeignException.class.cast(exception);
        LOGGER.warn("Unhandled Feign exception, of status {}", Integer.valueOf(feignException.status()), feignException);
        return Response.status(feignException.status()).entity(feignException).type(MediaType.APPLICATION_JSON_TYPE).build();
    }
}
