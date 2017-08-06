package com.stayrascal.cloud.zuul.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.util.StringUtils;

import java.io.IOException;

public class CoreHttpRequestInterceptor implements ClientHttpRequestInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(CoreHttpRequestInterceptor.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        HttpRequestWrapper httpRequestWrapper = new HttpRequestWrapper(request);
        String header = StringUtils.collectionToDelimitedString(CoreHeaderInterceptor.label.get(), CoreHeaderInterceptor.HEADER_LABEL_SPLIT);
        LOGGER.info("label: {}", header);
        httpRequestWrapper.getHeaders().add(CoreHeaderInterceptor.HEADER_LABEL, header);
        return execution.execute(httpRequestWrapper, body);
    }
}
