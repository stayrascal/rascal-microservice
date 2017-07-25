package com.stayrascal.cloud.auth.contract.dto;

import com.stayrascal.cloud.auth.contract.enumeration.AuthenticationType;

import java.util.List;

public class AuthenticationDto extends IdentityDto {
    private String id;
    private AuthenticationType authenticationType;
    private String authenticationName;
    private List<AuthenticationKeyDto> authenticationKeys;

    public AuthenticationDto() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AuthenticationType getAuthenticationType() {
        return this.authenticationType;
    }

    public void setAuthenticationType(AuthenticationType authenticationType) {
        this.authenticationType = authenticationType;
    }

    public String getAuthenticationName() {
        return this.authenticationName;
    }

    public void setAuthenticationName(String authenticationName) {
        this.authenticationName = authenticationName;
    }

    public List<AuthenticationKeyDto> getAuthenticationKeys() {
        return this.authenticationKeys;
    }

    public void setAuthenticationKeys(List<AuthenticationKeyDto> authenticationKeys) {
        this.authenticationKeys = authenticationKeys;
    }
}
