package com.stayrascal.cloud.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import zipkin.server.EnableZipkinServer;

@EnableZipkinServer
@EnableDiscoveryClient
@SpringBootApplication
public class InfraZipkinServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(InfraZipkinServerApplication.class);
    }
}
