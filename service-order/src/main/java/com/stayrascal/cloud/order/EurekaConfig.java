package com.stayrascal.cloud.order;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.stayrascal.cloud.product.contract.client")
@ConditionalOnProperty(name = "cloud.eureka.enabled", matchIfMissing = true)
public class EurekaConfig {
}
