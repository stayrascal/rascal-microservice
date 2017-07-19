package com.stayrscal.cloud.user.auth.contract.command;

import com.stayrascal.clould.common.contract.auth.IdentityType;
import com.stayrscal.cloud.user.auth.contract.AuthenticationType;

import java.util.Date;

public class CreateAuthenticationCommand {
    private IdentityType identityType;

    private String identityId;

    private AuthenticationType authenticationType;

    private String authenticationName;

    private String primaryKeyValue;

    private Date primaryKeyExpireTime;

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

    public String getPrimaryKeyValue() {
        return primaryKeyValue;
    }

    public void setPrimaryKeyValue(String primaryKeyValue) {
        this.primaryKeyValue = primaryKeyValue;
    }

    public Date getPrimaryKeyExpireTime() {
        return primaryKeyExpireTime;
    }

    public void setPrimaryKeyExpireTime(Date primaryKeyExpireTime) {
        this.primaryKeyExpireTime = primaryKeyExpireTime;
    }
}
