package com.stayrascal.cloud.user.auth.contract.dto;

import com.stayrascal.cloud.common.contract.auth.Identity;
import com.stayrascal.cloud.user.auth.contract.AuthenticationType;

import java.util.List;

public class AuthenticationDto extends Identity {
    private String id;

    private AuthenticationType authenticationType;

    private String authenticationName;

    private List<AuthenticationKeyDto> authenticationKeys;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AuthenticationType getAuthenticationType() {
        return authenticationType;
    }

    public void setAuthenticationType(AuthenticationType authenticationType) {
        this.authenticationType = authenticationType;
    }

    public String getAuthenticationName() {
        return authenticationName;
    }

    public void setAuthenticationName(String authenticationName) {
        this.authenticationName = authenticationName;
    }

    public List<AuthenticationKeyDto> getAuthenticationKeys() {
        return authenticationKeys;
    }

    public void setAuthenticationKeys(List<AuthenticationKeyDto> authenticationKeys) {
        this.authenticationKeys = authenticationKeys;
    }
}
