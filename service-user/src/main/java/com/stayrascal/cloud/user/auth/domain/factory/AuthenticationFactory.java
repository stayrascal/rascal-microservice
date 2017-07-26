package com.stayrascal.cloud.user.auth.domain.factory;

import com.stayrascal.cloud.common.constant.ErrorCode;
import com.stayrascal.cloud.common.jersey.exception.InternalErrorException;
import com.stayrascal.cloud.common.jpa.UniqueKeyGenerator;
import com.stayrascal.cloud.common.mapper.DefaultMapper;
import com.stayrascal.cloud.user.auth.contract.command.CreateAuthenticationCommand;
import com.stayrascal.cloud.user.auth.domain.entity.Authentication;
import com.stayrascal.cloud.user.auth.domain.entity.EmailVerificationCodeAuthentication;
import com.stayrascal.cloud.user.auth.domain.entity.PasswordAuthentication;
import com.stayrascal.cloud.user.auth.domain.service.RandomKeyService;
import com.stayrascal.cloud.user.auth.external.adapter.EmailServiceAdapter;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFactory {
    private final UniqueKeyGenerator keyGenerator;
    private final RandomKeyService randomKeyService;
    private final EmailServiceAdapter emailServiceAdapter;
    private final DefaultMapper mapper;

    @Value("${cloud.auth.email.defaultKeyExpireMinutes}")
    private Integer emailKeyDefaultExpireMinutes;

    public AuthenticationFactory(UniqueKeyGenerator keyGenerator, RandomKeyService randomKeyService, EmailServiceAdapter emailServiceAdapter) {
        this.keyGenerator = keyGenerator;
        this.randomKeyService = randomKeyService;
        this.emailServiceAdapter = emailServiceAdapter;
        this.mapper = DefaultMapper.builder()
                .register(CreateAuthenticationCommand.class, EmailVerificationCodeAuthentication.class)
                .register(CreateAuthenticationCommand.class, PasswordAuthentication.class)
                .build();
    }

    public Authentication create(CreateAuthenticationCommand command) {
        Authentication authentication;
        switch (command.getAuthenticationType()) {
            case PASSWORD:
                authentication = mapper.map(command, PasswordAuthentication.class);
                authentication.createAuthenticationKey(command.getPrimaryKeyValue(), command.getPrimaryKeyExpireTime());
                break;
            case EMAIL_TRANSIENT_KEY:
                authentication = createEmailAuth(command);
                break;
            default:
                throw new InternalErrorException(ErrorCode.INTERNAL_ERROR, "Not supported authentication type {}", command.getAuthenticationType());
        }
        authentication.setId(keyGenerator.generateKey());
        return authentication;
    }

    private Authentication createEmailAuth(CreateAuthenticationCommand command) {
        EmailVerificationCodeAuthentication emailAuth = mapper.map(command, EmailVerificationCodeAuthentication.class);
        emailAuth.setRandomKeyService(randomKeyService);
        emailAuth.setEmailServiceAdapter(emailServiceAdapter);

        if (!Strings.isNullOrEmpty(command.getPrimaryKeyValue())) {
            emailAuth.createAuthenticationKey(command.getPrimaryKeyValue(), command.getPrimaryKeyExpireTime());
        }
        return emailAuth;
    }


}
