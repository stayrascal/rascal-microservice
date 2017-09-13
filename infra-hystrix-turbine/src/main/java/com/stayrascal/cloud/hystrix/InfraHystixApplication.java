package com.stayrascal.cloud.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@EnableTurbine
@SpringBootApplication
public class InfraHystixApplication {

    public static void main(String[] args) {
        SpringApplication.run(InfraHystixApplication.class);
    }
}
