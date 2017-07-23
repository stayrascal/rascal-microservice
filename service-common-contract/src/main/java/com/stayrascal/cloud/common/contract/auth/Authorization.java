package com.stayrascal.cloud.common.contract.auth;

public class Authorization {
    private String organizationId;
    private String roleId;

    public Authorization() {
    }

    public Authorization(String organizationId, String roleId) {
        this.organizationId = organizationId;
        this.roleId = roleId;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
