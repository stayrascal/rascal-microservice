package com.stayrascal.cloud.config.configuration;

import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;

//@Configuration
public class CommonConfiguration {
    @Bean
    public AlwaysSampler defaultSampler() {
        return new AlwaysSampler();
    }
}
