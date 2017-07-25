package com.stayrascal.cloud.auth.contract.dto;

import com.stayrascal.cloud.auth.contract.enumeration.AccountGroup;
import com.stayrascal.cloud.auth.contract.enumeration.AccountRole;
import com.stayrascal.cloud.auth.contract.enumeration.Permission;

import java.util.List;

public class IdentityDto {
    private String accountId;
    private AccountRole accountRole;
    private AccountGroup accountGroup;
    private List<Permission> permissions;

    public IdentityDto() {
    }

    public IdentityDto(String accountId, AccountRole accountRole, AccountGroup accountGroup, List<Permission> permissions) {
        this.accountId = accountId;
        this.accountRole = accountRole;
        this.accountGroup = accountGroup;
        this.permissions = permissions;
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

    public List<Permission> getPermissions() {
        return this.permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
