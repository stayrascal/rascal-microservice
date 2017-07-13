package com.stayrascal.cloud.common.lib.jpa;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GuidBasedUniqueKeyGenerator implements UniqueKeyGenerator {

    public GuidBasedUniqueKeyGenerator() {
    }

    @Override
    public String generateKey() {
        return UUID.randomUUID().toString();
    }
}
