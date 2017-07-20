package com.stayrscal.cloud.user.auth.contract.dto;

import java.util.Date;
import java.util.List;

public class RoleDto {
    private String id;

    private String name;

    private List<RolePermissionDto> permissions;

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

    public List<RolePermissionDto> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<RolePermissionDto> permissions) {
        this.permissions = permissions;
    }

    public Date getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }

    public class RolePermissionDto {
        private Long id;

        private String permissionType;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getPermissionType() {
            return permissionType;
        }

        public void setPermissionType(String permissionType) {
            this.permissionType = permissionType;
        }
    }
}
