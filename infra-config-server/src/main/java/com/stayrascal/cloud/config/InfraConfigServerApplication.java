package com.stayrascal.cloud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableDiscoveryClient
@EnableConfigServer
@SpringBootConfiguration
public class InfraConfigServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(InfraConfigServerApplication.class, args);
    }
}
