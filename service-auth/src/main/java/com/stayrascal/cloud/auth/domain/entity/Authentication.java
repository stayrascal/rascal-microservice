package com.stayrascal.cloud.auth.domain.entity;

import com.stayrascal.cloud.auth.contract.dto.AuthenticationDto;
import com.stayrascal.cloud.common.contract.enumeration.CommonStatus;

import java.util.function.Consumer;

public class Authentication {
    private String id;

    private CommonStatus status;

    private final AuthenticationDtoMapper mapper;

    private Consumer<Authentication> notifyChange;

    public Authentication() {
        mapper = new AuthenticationDtoMapper();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CommonStatus getStatus() {
        return status;
    }

    public void setStatus(CommonStatus status) {
        this.status = status;
    }

    public void setNotifyChange(Consumer<Authentication> notifyChange) {
        this.notifyChange = notifyChange;
    }

    public void updateAuth(AuthenticationDto auth) {
        notifyChange.accept(this);
    }

    public AuthenticationDto toDto() {
        return mapper.map(this, AuthenticationDto.class);
    }
}
