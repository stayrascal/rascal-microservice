package com.stayrascal.cloud.user.auth.facade;

import com.stayrascal.cloud.common.constant.DefaultValues;
import com.stayrascal.cloud.common.constant.ErrorCode;
import com.stayrascal.cloud.common.contract.auth.Identity;
import com.stayrascal.cloud.common.contract.auth.IdentityType;
import com.stayrascal.cloud.common.contract.enumeration.SortType;
import com.stayrascal.cloud.common.jersey.exception.NotFoundException;
import com.stayrascal.cloud.common.jersey.exception.ServerErrorException;
import com.stayrascal.cloud.common.util.JwtTokenCodec;
import com.stayrascal.cloud.user.admin.contract.dto.StaffDto;
import com.stayrascal.cloud.user.admin.facade.StaffFacade;
import com.stayrascal.cloud.user.auth.contract.AuthenticationType;
import com.stayrascal.cloud.user.auth.contract.command.CreateAuthenticationCommand;
import com.stayrascal.cloud.user.auth.contract.command.CreateAuthenticationKeyCommand;
import com.stayrascal.cloud.user.auth.contract.command.CreateTokenCommand;
import com.stayrascal.cloud.user.auth.contract.dto.AuthenticationDto;
import com.stayrascal.cloud.user.auth.domain.entity.Authentication;
import com.stayrascal.cloud.user.auth.domain.entity.AuthenticationKey;
import com.stayrascal.cloud.user.auth.domain.factory.AuthenticationFactory;
import com.stayrascal.cloud.user.auth.domain.repository.AuthenticationRepository;
import com.stayrascal.cloud.user.auth.mapper.AuthenticationDtoMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional
public class AuthFacade {
    private final AuthenticationFactory authenticationFactory;
    private final AuthenticationRepository authenticationRepository;
    private final JwtTokenCodec jwtTokenCodec;
    private final AuthenticationDtoMapper authenticationDtoMapper;
    private final StaffFacade staffFacade;

    @Autowired
    public AuthFacade(AuthenticationFactory authenticationFactory,
                      AuthenticationRepository authenticationRepository,
                      AuthenticationDtoMapper authenticationDtoMapper,
                      StaffFacade staffFacade) {
        this.authenticationFactory = authenticationFactory;
        this.authenticationRepository = authenticationRepository;
        this.jwtTokenCodec = new JwtTokenCodec(DefaultValues.Auth.tokenSecret(),
                DefaultValues.Auth.tokenExpiredMinute());
        this.authenticationDtoMapper = authenticationDtoMapper;
        this.staffFacade = staffFacade;
    }

    public String createAuthentication(CreateAuthenticationCommand command) {
        Authentication authentication = authenticationFactory.create(command);

        return authenticationRepository.insert(authentication);
    }

    public void delete(String authenticationId) {
        Authentication authentication = tryGetAuthentication(authenticationId);
        authenticationRepository.remove(authentication);
    }

    public Long createAuthenticationKey(String authenticationId, CreateAuthenticationKeyCommand command) {
        Authentication authentication = tryGetAuthentication(authenticationId);
        AuthenticationKey authenticationKey = authentication.createAuthenticationKey(command.getKeyValue(),
                command.getExpiredTime());

        authenticationRepository.update(authentication);
        return authenticationKey.getId();
    }

    public String createToken(String authenticationId, CreateTokenCommand command) {
        Authentication authentication = tryGetAuthentication(authenticationId);
        authentication.authenticate(command.getKey());

        // transient key may be updated
        authenticationRepository.update(authentication);

        Identity identity;
        IdentityType identityType = authentication.getIdentityType();
        String identityId = authentication.getIdentityId();
        switch (identityType) {
            case MEMBER: {
                identity = new Identity(identityType, identityId);
                break;
            }
            case STAFF: {
                StaffDto staff = staffFacade.getStaff(identityId);
                identity = new Identity(identityType, identityId, staff.getAuthorizations());
                break;
            }
            default:
                throw new ServerErrorException(ErrorCode.INTERNAL_ERROR,
                        "Identity type {} not supported!", identityType);
        }
        return jwtTokenCodec.encodeToken(identity);
    }

    public Identity getTokenEncodedIdentity(String authenticationId, String token) {
        tryGetAuthentication(authenticationId);
        return jwtTokenCodec.decodeToken(token);
    }

    private Authentication tryGetAuthentication(String authenticationId) {
        return authenticationRepository.ofId(authenticationId).orElseThrow(() ->
                new NotFoundException(ErrorCode.INTERNAL_ERROR, "Authentication of id {} not found", authenticationId)
        );
    }

    public Long countAuthentications(IdentityType identityType, String identityId, String authenticationName,
                                     AuthenticationType authenticationType) {
        return authenticationRepository.countAuthentications(identityType, identityId,
                authenticationName, authenticationType);
    }

    public List<AuthenticationDto> listAuthentications(Integer pageSize, Integer pageIndex,
                                                       SortType sortType, String sortBy,
                                                       IdentityType identityType, String identityId,
                                                       String authenticationName,
                                                       AuthenticationType authenticationType) {
        List<Authentication> authentications = authenticationRepository.listAuthentications(pageSize, pageIndex,
                sortType, sortBy, identityType, identityId, authenticationName, authenticationType);

        return authentications.stream().map(authenticationDtoMapper::authenticationToDto).collect(Collectors.toList());
    }

    public AuthenticationDto getAuthentication(String authenticationId) {
        Authentication authentication = authenticationRepository.ofId(authenticationId).orElseThrow(() ->
                new NotFoundException(ErrorCode.INTERNAL_ERROR, "Authenticatio of id {} not found!", authenticationId));

        return authenticationDtoMapper.authenticationToDto(authentication);
    }
}
