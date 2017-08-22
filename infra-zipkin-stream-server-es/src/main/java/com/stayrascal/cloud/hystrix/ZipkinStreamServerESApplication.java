package com.stayrascal.cloud.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.zipkin.stream.EnableZipkinStreamServer;

@EnableZipkinStreamServer
@SpringBootApplication
public class ZipkinStreamServerESApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZipkinStreamServerESApplication.class);
    }
}
