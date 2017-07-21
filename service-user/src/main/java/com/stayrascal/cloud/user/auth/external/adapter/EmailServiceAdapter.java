package com.stayrascal.cloud.user.auth.external.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceAdapter.class);

    @Value("${cloud.auth.email.title}")
    private String emailTitle;

    @Value("${cloud.auth.email.contentTemplate}")
    private String contentTemplate;

    public void sendVerificationCode(String sentTo, String verificationCode) {
        LOGGER.info("Send email verification code to {}, code: {}", sentTo, verificationCode);
    }
}
