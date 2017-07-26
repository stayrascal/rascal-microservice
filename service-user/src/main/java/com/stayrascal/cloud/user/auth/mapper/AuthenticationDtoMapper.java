package com.stayrascal.cloud.user.auth.mapper;

import com.stayrascal.cloud.common.mapper.DefaultMapper;
import com.stayrascal.cloud.user.auth.contract.dto.AuthenticationDto;
import com.stayrascal.cloud.user.auth.contract.dto.AuthenticationKeyDto;
import com.stayrascal.cloud.user.auth.domain.entity.Authentication;
import com.stayrascal.cloud.user.auth.domain.entity.AuthenticationKey;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthenticationDtoMapper extends DefaultMapper {
    public AuthenticationDtoMapper() {
        register(AuthenticationDto.class, Authentication.class);
        register(AuthenticationKeyDto.class, AuthenticationKey.class);
    }

    public AuthenticationDto authenticationToDto(Authentication authentication) {
        AuthenticationDto authenticationDto = map(authentication, AuthenticationDto.class);
        List<AuthenticationKeyDto> keys = authentication.getAuthenticationKeys().stream()
                .map(this::authenticationKeyToDto).collect(Collectors.toList());
        authenticationDto.setAuthenticationKeys(keys);
        return authenticationDto;
    }

    private AuthenticationKeyDto authenticationKeyToDto(AuthenticationKey authenticationKey) {
        return map(authenticationKey, AuthenticationKeyDto.class);
    }
}
