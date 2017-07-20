package com.stayrascal.cloud.user.auth.domain.entity;

import com.stayrascal.clould.common.contract.auth.IdentityType;
import com.stayrscal.cloud.user.auth.contract.AuthenticationType;

import java.util.Date;
import java.util.List;

public abstract class Authentication {
    private String id;

    private IdentityType identityType;

    private String identityId;

    private AuthenticationType authenticationType;

    // used for authentication identity, for example, if auth type is email, then this hold the email address
    private String authenticationName;

    private List<AuthenticationKey> authenticationKeys;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public IdentityType getIdentityType() {
        return identityType;
    }

    public void setIdentityType(IdentityType identityType) {
        this.identityType = identityType;
    }

    public String getIdentityId() {
        return identityId;
    }

    public void setIdentityId(String identityId) {
        this.identityId = identityId;
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

    public List<AuthenticationKey> getAuthenticationKeys() {
        return authenticationKeys;
    }

    public void setAuthenticationKeys(List<AuthenticationKey> authenticationKeys) {
        this.authenticationKeys = authenticationKeys;
    }

    public abstract AuthenticationKey createAuthenticationKey(String key, Date expiredTime);

    public abstract void authenticate(String key);
}
