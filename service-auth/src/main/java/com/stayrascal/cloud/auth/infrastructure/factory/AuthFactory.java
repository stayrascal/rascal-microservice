package com.stayrascal.cloud.auth.infrastructure.factory;

import com.stayrascal.cloud.auth.contract.dto.AuthenticationDto;
import com.stayrascal.cloud.auth.domain.entity.Authentication;
import com.stayrascal.cloud.auth.infrastructure.persistence.AuthenticationPo;
import com.stayrascal.cloud.auth.infrastructure.persistence.AuthenticationPoMapper;
import com.stayrascal.cloud.auth.infrastructure.persistence.AuthenticationRepository;
import com.stayrascal.cloud.common.contract.enumeration.CommonStatus;
import com.stayrascal.cloud.common.ddd.EventSender;
import com.stayrascal.cloud.common.jpa.UniqueKeyGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthFactory {
    private final UniqueKeyGenerator uniqueKeyGenerator;
    private final EventSender eventSender;
    private final AuthenticationPoMapper mapper;
    private final AuthenticationRepository authenticationRepository;

    @Autowired
    public AuthFactory(UniqueKeyGenerator uniqueKeyGenerator,
                       AuthenticationRepository authenticationRepository,
                       EventSender eventSender) {
        this.uniqueKeyGenerator = uniqueKeyGenerator;
        this.authenticationRepository = authenticationRepository;
        this.eventSender = eventSender;
        this.mapper = new AuthenticationPoMapper();
    }

    public Authentication createWithDto(AuthenticationDto authDto) {
        AuthenticationPo auth = mapper.map(authDto, AuthenticationPo.class);
        auth.setId(uniqueKeyGenerator.generateKey());
        auth.setStatus(CommonStatus.ENABLED);
        authenticationRepository.save(auth);

        return toAuth(auth).get();
    }

    public Optional<Authentication> createWithId(String id) {
        return toAuth(authenticationRepository.ofId(id));
    }

    private Optional<Authentication> toAuth(AuthenticationPo authPo) {
        if (authPo == null) {
            return Optional.empty();
        }
        Authentication authentication = mapper.map(authPo, Authentication.class);
        authentication.setNotifyChange(entity -> authenticationRepository.update(mapper.map(entity, AuthenticationPo.class)));
        return Optional.of(authentication);
    }
}
