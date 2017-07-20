package com.stayrascal.cloud.user.auth.domain.entity;

import com.stayrascal.cloud.common.constant.ErrorCode;
import com.stayrascal.cloud.common.jersey.exception.BadRequestException;
import com.stayrascal.cloud.user.auth.domain.service.RandomKeyService;
import com.stayrascal.cloud.user.auth.external.adapter.EmailServiceAdapter;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Date;

public class EmailVerificationCodeAuthentication extends Authentication {

    private RandomKeyService randomKeyService;

    private EmailServiceAdapter emailServiceAdapter;

    public RandomKeyService getRandomKeyService() {
        return randomKeyService;
    }

    public void setRandomKeyService(RandomKeyService randomKeyService) {
        this.randomKeyService = randomKeyService;
    }

    public EmailServiceAdapter getEmailServiceAdapter() {
        return emailServiceAdapter;
    }

    public void setEmailServiceAdapter(EmailServiceAdapter emailServiceAdapter) {
        this.emailServiceAdapter = emailServiceAdapter;
    }

    @Override
    public AuthenticationKey createAuthenticationKey(String key, Date expiredTime) {
        AuthenticationKey authenticationKey = new AuthenticationKey(randomKeyService.generate(), expiredTime);
        if (getAuthenticationKeys() == null) {
            setAuthenticationKeys(new ArrayList<>());
        }
        getAuthenticationKeys().add(authenticationKey);
        emailServiceAdapter.sendVerificationCode(getAuthenticationName(), authenticationKey.getKeyValue());
        return authenticationKey;
    }

    @Override
    public void authenticate(String key) {
        if (getAuthenticationKeys() == null || getAuthenticationKeys().isEmpty()) {
            throw new BadRequestException(ErrorCode.INTERNAL_ERROR,
                    "No verification code generated of auth id {}", getId());
        }

        AuthenticationKey matchedKey = getAuthenticationKeys().stream()
                .filter(k -> k.getKeyValue().equals(key)).findFirst()
                .orElseThrow(() -> new BadRequestException(ErrorCode.INTERNAL_ERROR,
                        "Verification code not matching, of auth id {}", getId()));

        if (matchedKey.getExpiredTime().before(DateTime.now().toDate())) {
            throw new BadRequestException(ErrorCode.INTERNAL_ERROR,
                    "Verification code expired {}, of auth id {}", matchedKey.getExpiredTime(), getId());
        }

        // TODO: remove the expired keys
    }
}
