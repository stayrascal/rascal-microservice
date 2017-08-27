package com.stayrascal.cloud.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerformanceLogAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(PerformanceLogAspect.class);

    public Object logPerformance(ProceedingJoinPoint joinPoint) throws Throwable {
        Object value = joinPoint.proceed();
        LOGGER.info("{} has been invoked", joinPoint.getSignature().getName());
        return value;
    }
}
