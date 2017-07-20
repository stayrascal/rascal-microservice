package com.stayrascal.cloud.user.auth.mapper;

import com.stayrascal.cloud.common.constant.ErrorCode;
import com.stayrascal.cloud.common.jersey.exception.InternalErrorException;
import com.stayrascal.cloud.common.mapper.DefaultMapper;
import com.stayrascal.cloud.user.auth.domain.entity.Authentication;
import com.stayrascal.cloud.user.auth.domain.entity.AuthenticationKey;
import com.stayrascal.cloud.user.auth.domain.entity.EmailVerificationCodeAuthentication;
import com.stayrascal.cloud.user.auth.domain.entity.PasswordAuthentication;
import com.stayrascal.cloud.user.auth.domain.service.RandomKeyService;
import com.stayrascal.cloud.user.auth.external.adapter.EmailServiceAdapter;
import com.stayrascal.cloud.user.auth.infrastructure.persistence.po.AuthenticationKeyPo;
import com.stayrascal.cloud.user.auth.infrastructure.persistence.po.AuthenticationPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthenticationPoMapper extends DefaultMapper {
    @Value("${cloud.auth.email.defaultKeyExpireMinutes}")
    private Integer emailKeyDefaultExpireMinutes;

    private final RandomKeyService randomKeyService;

    private final EmailServiceAdapter emailServiceAdapter;

    @Autowired
    public AuthenticationPoMapper(RandomKeyService randomKeyService, EmailServiceAdapter emailServiceAdapter) {
        this.randomKeyService = randomKeyService;
        this.emailServiceAdapter = emailServiceAdapter;
        register(AuthenticationPo.class, Authentication.class);
        register(AuthenticationKeyPo.class, AuthenticationKey.class);
    }

    public Authentication authenticationFromPo(AuthenticationPo authenticationPo) {
        Authentication authentication;

        switch (authenticationPo.getAuthenticationType()) {
            case PASSWORD:
                authentication = map(authenticationPo, PasswordAuthentication.class);
                break;
            case EMAIL_TRANSIENT_KEY:
                authentication = mapEmailAuthentication(authenticationPo);
                break;
            default:
                throw new InternalErrorException(ErrorCode.INTERNAL_ERROR,
                        "Unsupported authentication type from db {}, of id {}",
                        authenticationPo.getAuthenticationType(),
                        authenticationPo.getId());
        }

        return authentication;
    }

    private EmailVerificationCodeAuthentication mapEmailAuthentication(AuthenticationPo authenticationPo) {
        EmailVerificationCodeAuthentication emailAuth = map(authenticationPo,
                EmailVerificationCodeAuthentication.class);

        emailAuth.setEmailServiceAdapter(emailServiceAdapter);
        emailAuth.setRandomKeyService(randomKeyService);
        List<AuthenticationKey> authenticationKeys = authenticationPo.getAuthenticationKeys().stream()
                .map(this::transientKeyFromPo).collect(Collectors.toList());
        emailAuth.setAuthenticationKeys(authenticationKeys);

        return emailAuth;
    }

    private AuthenticationKey transientKeyFromPo(AuthenticationKeyPo po) {
        return map(po, AuthenticationKey.class);
    }

    public AuthenticationPo authenticationToPo(Authentication authentication) {
        AuthenticationPo authenticationPo = map(authentication, AuthenticationPo.class);
        String authenticationId = authentication.getId();
        List<AuthenticationKeyPo> authenticationKeyPoList = authentication.getAuthenticationKeys().stream()
                .map(k -> transientKeyToPo(authenticationId, k)).collect(Collectors.toList());
        authenticationPo.setAuthenticationKeys(authenticationKeyPoList);
        return authenticationPo;
    }

    private AuthenticationKeyPo transientKeyToPo(String authenticationId, AuthenticationKey authenticationKey) {
        AuthenticationKeyPo authenticationKeyPo = map(authenticationKey, AuthenticationKeyPo.class);
        authenticationKeyPo.setAuthenticationId(authenticationId);
        return authenticationKeyPo;
    }
}
