package com.stayrascal.cloud.admin;

import com.stayrascal.cloud.admin.filter.AccessFilter;
import com.stayrascal.cloud.admin.filter.DynamicRoutesFilter;

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

    @Bean
    public DynamicRoutesFilter dynamicRoutesFilter() {
        return new DynamicRoutesFilter();
    }
}
