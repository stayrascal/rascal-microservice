package com.stayrascal.cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.feign.ribbon.CachingSpringLoadBalancerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@ConditionalOnProperty(name = "cloud.eureka.enabled", matchIfMissing = true)
public class FeignWarmUp {
    @Autowired
    private ApplicationContext context;

    @Autowired
    private CachingSpringLoadBalancerFactory factory;

    @PostConstruct
    public void feignClientWarmUpAroundAdvice() {
        context.getBeansWithAnnotation(FeignClient.class)
                .keySet()
                .forEach(beanName -> factory.create(context.findAnnotationOnBean(beanName, FeignClient.class).name()));
    }
}
