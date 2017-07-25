package com.stayrascal.cloud.auth.contract.command;

import com.stayrascal.cloud.auth.contract.enumeration.AccountGroup;
import com.stayrascal.cloud.auth.contract.enumeration.AccountRole;
import com.stayrascal.cloud.auth.contract.enumeration.AuthenticationType;

import java.util.Date;

public class CreateAuthenticationCommand {
    private String accountId;
    private AccountRole accountRole;
    private AccountGroup accountGroup;
    private AuthenticationType authenticationType;
    private String authenticationName;
    private String primaryKeyValue;
    private Date primaryKeyExpireTime;

    public CreateAuthenticationCommand() {
    }

    public CreateAuthenticationCommand(String accountId, AuthenticationType authenticationType, AccountRole accountRole, AccountGroup accountGroup, String authenticationName, String primaryKeyValue) {
        this.accountId = accountId;
        this.authenticationType = authenticationType;
        this.accountRole = accountRole;
        this.accountGroup = accountGroup;
        this.authenticationName = authenticationName;
        this.primaryKeyValue = primaryKeyValue;
    }

    public String getAccountId() {
        return this.accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public AccountRole getAccountRole() {
        return this.accountRole;
    }

    public void setAccountRole(AccountRole accountRole) {
        this.accountRole = accountRole;
    }

    public AccountGroup getAccountGroup() {
        return this.accountGroup;
    }

    public void setAccountGroup(AccountGroup accountGroup) {
        this.accountGroup = accountGroup;
    }

    public String getAuthenticationName() {
        return this.authenticationName;
    }

    public void setAuthenticationName(String authenticationName) {
        this.authenticationName = authenticationName;
    }

    public String getPrimaryKeyValue() {
        return this.primaryKeyValue;
    }

    public void setPrimaryKeyValue(String primaryKeyValue) {
        this.primaryKeyValue = primaryKeyValue;
    }

    public AuthenticationType getAuthenticationType() {
        return this.authenticationType;
    }

    public void setAuthenticationType(AuthenticationType authenticationType) {
        this.authenticationType = authenticationType;
    }

    public Date getPrimaryKeyExpireTime() {
        return this.primaryKeyExpireTime;
    }

    public void setPrimaryKeyExpireTime(Date primaryKeyExpireTime) {
        this.primaryKeyExpireTime = primaryKeyExpireTime;
    }
}
