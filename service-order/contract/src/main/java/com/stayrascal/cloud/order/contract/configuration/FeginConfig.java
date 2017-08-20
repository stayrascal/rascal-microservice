package com.stayrascal.cloud.order.contract.configuration;

import static java.util.concurrent.TimeUnit.SECONDS;

import feign.Request;
import feign.Retryer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeginConfig {

    @Value("${ribbon.retry.period:100}")
    private long retryPeriod;

    @Value("${ribbon.retry.maxPeriod:1}")
    private long maxRetryPeriod;

    @Value("${ribbon.retry.maxAttempts:5}")
    private int maxRetryAttempts;

    @Value("${ribbon.ReadTimeout:6000}")
    private int ribbonReadTimeout;

    @Value("${ribbon.ConnectTimeout:3000}")
    private int ribbonConnectionTimeout;

    @Bean
    public Retryer feginRetryer() {
        Retryer retryer = new Retryer.Default(retryPeriod, SECONDS.toMillis(maxRetryPeriod), maxRetryAttempts);
        return retryer;
    }

    @Bean
    public Request.Options feginOption() {
        return new Request.Options(ribbonConnectionTimeout, ribbonReadTimeout);
    }
}
