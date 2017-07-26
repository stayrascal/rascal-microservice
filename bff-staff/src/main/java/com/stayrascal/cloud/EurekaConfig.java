package com.stayrascal.cloud;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableEurekaClient
@EnableFeignClients(basePackages = {
        "com.stayrascal.cloud.address.contract.client",
        "com.stayrascal.cloud.user.member.contract.client",
        "com.stayrascal.cloud.user.auth.contract.client",
        "com.stayrascal.cloud.user.admin.contract.client",
        "com.stayrascal.cloud.order.contract.client",
        "com.stayrascal.cloud.product.contract.client",
        "com.stayrascal.cloud.organization.contract.client",
})
@ConditionalOnProperty(name = "cloud.eureka.enabled", matchIfMissing = true)
public class EurekaConfig {
}
