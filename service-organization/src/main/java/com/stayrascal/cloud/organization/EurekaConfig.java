package com.stayrascal.cloud.organization;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableEurekaClient
@ConditionalOnProperty(name = "cloud.eureka.enabled", matchIfMissing = true)
public class EurekaConfig {
}
