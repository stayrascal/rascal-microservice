package com.stayrascal.cloud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class InfraConfigServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(InfraConfigServerApplication.class, args);
    }
}
