package com.stayrascal.cloud.user.auth.domain.service;

import com.google.common.base.Joiner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class RandomKeyService {

    @Value("${cloud.auth.randomKeyLength}")
    private Integer randomKeychainLength;

    public String generate() {
        return Joiner.on("")
                .join(Stream.generate(() -> ThreadLocalRandom.current().nextInt(0, 10))
                        .limit(randomKeychainLength).collect(Collectors.toList()));
    }
}
