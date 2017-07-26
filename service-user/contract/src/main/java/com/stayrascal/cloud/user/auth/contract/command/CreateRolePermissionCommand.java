package com.stayrascal.cloud.user.auth.contract.command;

public class CreateRolePermissionCommand {
    private String permissionType;

    public String getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(String permissionType) {
        this.permissionType = permissionType;
    }
}
