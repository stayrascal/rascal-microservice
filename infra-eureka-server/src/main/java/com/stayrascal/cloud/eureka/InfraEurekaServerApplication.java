package com.stayrascal.cloud.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootConfiguration
public class InfraEurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(InfraEurekaServerApplication.class, args);
    }
}
