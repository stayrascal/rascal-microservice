package com.stayrascal.cloud.zuul;

import com.stayrascal.cloud.zuul.filter.AccessFilter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@SpringBootApplication
public class InfraZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(InfraZuulApplication.class, args);
    }

    @Bean
    public AccessFilter accessFilter() {
        return new AccessFilter();
    }
}
