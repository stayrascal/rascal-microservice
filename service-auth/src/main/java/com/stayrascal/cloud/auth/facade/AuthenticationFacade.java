package com.stayrascal.cloud.auth.facade;

import com.stayrascal.cloud.auth.contract.command.CreateAuthenticationCommand;
import com.stayrascal.cloud.auth.contract.dto.AuthenticationDto;
import com.stayrascal.cloud.auth.infrastructure.factory.AuthFactory;
import com.stayrascal.cloud.common.constant.ErrorCode;
import com.stayrascal.cloud.common.ddd.EventSender;
import com.stayrascal.cloud.common.jersey.exception.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class AuthenticationFacade {
    private final AuthFactory authFactory;
    private final EventSender eventSender;

    @Autowired
    public AuthenticationFacade(AuthFactory authFactory,
                                EventSender eventSender) {
        this.authFactory = authFactory;
        this.eventSender = eventSender;
    }

    public AuthenticationDto getAuthById(String id) {
        return authFactory.createWithId(id).orElseThrow(() ->
                new NotFoundException(ErrorCode.INTERNAL_ERROR, "Authentication of id {} not found", id)
        ).toDto();
    }

    public String createAuth(CreateAuthenticationCommand createAuthenticationCommand) {
        return null;
    }
}
