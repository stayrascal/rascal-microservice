package com.stayrascal.cloud.auth.infrastructure.persistence;

import com.stayrascal.cloud.auth.domain.entity.Authentication;
import com.stayrascal.cloud.common.mapper.DefaultMapper;

public class AuthenticationPoMapper extends DefaultMapper {
    public AuthenticationPoMapper() {
        register(AuthenticationPo.class, Authentication.class);
    }
}
