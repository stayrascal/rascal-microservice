package com.stayrascal.cloud.auth.domain.entity;

import com.stayrascal.cloud.auth.contract.dto.AuthenticationDto;
import com.stayrascal.cloud.common.mapper.DefaultMapper;

class AuthenticationDtoMapper extends DefaultMapper {
    public AuthenticationDtoMapper() {
        register(AuthenticationDto.class, Authentication.class);
    }
}
