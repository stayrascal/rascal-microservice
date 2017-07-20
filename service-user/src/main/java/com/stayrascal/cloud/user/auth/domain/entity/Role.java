package com.stayrascal.cloud.user.auth.domain.entity;

import java.util.Date;
import java.util.List;

public class Role {
    private String id;

    private String name;

    private List<RolePermission> rolePermissions;

    private Date timeCreated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RolePermission> getRolePermissions() {
        return rolePermissions;
    }

    public void setRolePermissions(List<RolePermission> rolePermissions) {
        this.rolePermissions = rolePermissions;
    }

    public Date getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }

    public RolePermission addPermission(String permissionType) {
        RolePermission rolePermission = new RolePermission();
        rolePermission.setPermissionType(permissionType);
        getRolePermissions().add(rolePermission);
        return rolePermission;
    }

    public void removePermission(String permissionId) {
        getRolePermissions().removeIf(p -> p.getId().equals(permissionId));
    }
}
