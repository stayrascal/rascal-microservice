package com.stayrascal.cloud.common.lib.jersey.exception.handler;

import com.netflix.hystrix.exception.HystrixRuntimeException;
import com.stayrascal.cloud.common.lib.jersey.exception.BaseException;
import com.stayrascal.cloud.common.lib.jersey.exception.RemoteCallException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class HystrixExceptionMapper implements ExceptionMapper<HystrixRuntimeException> {
    private static final Logger LOGGER = LoggerFactory.getLogger(HystrixExceptionMapper.class);
    private BaseExceptionMapper baseExceptionMapper = new BaseExceptionMapper();
    private RemoteCallExceptionMapper remoteCallExceptionMapper = new RemoteCallExceptionMapper();

    public HystrixExceptionMapper() {
    }

    public Response toResponse(HystrixRuntimeException exception) {
        LOGGER.warn("Unhandled Hystrix exception, of failure type {}", exception.getFailureType(), exception);
        Throwable cause = exception.getCause();
        return BaseException.class.isInstance(cause) ? this.baseExceptionMapper.toResponse((BaseException) BaseException.class.cast(cause)) : (RemoteCallException.class.isInstance(cause) ? this.remoteCallExceptionMapper.toResponse((RemoteCallException) RemoteCallException.class.cast(cause)) : Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(exception).type(MediaType.APPLICATION_JSON_TYPE).build());
    }
}
