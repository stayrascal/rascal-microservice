package com.stayrascal.cloud.common.lib.feign;

import com.google.common.io.CharStreams;
import com.stayrascal.cloud.common.lib.jersey.exception.RemoteCallException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static javax.ws.rs.core.Response.Status.fromStatusCode;

public class FeignErrorDecoder implements ErrorDecoder {
    private static final Logger LOGGER = LoggerFactory.getLogger(FeignErrorDecoder.class);
    private ErrorDecoder defaultDecoder = new Default();

    public FeignErrorDecoder() {
    }

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() >= 400 && response.status() <= 599) {
            LOGGER.debug("Feign error response decoder, of methodKey {}, of response status {}", methodKey, Integer.valueOf(response.status()));
            return new RemoteCallException(fromStatusCode(response.status()), this.getResponseMessage(response));
        } else {
            return this.defaultDecoder.decode(methodKey, response);
        }
    }

    private String getResponseMessage(Response response) {
        try {
            return CharStreams.toString(response.body().asReader());
        } catch (IOException e) {
            LOGGER.debug("Failed to get error response body due to exception", e);
            return response.body().toString();
        }
    }
}
