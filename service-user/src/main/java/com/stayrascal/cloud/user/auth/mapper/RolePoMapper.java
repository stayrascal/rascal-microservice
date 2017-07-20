package com.stayrascal.cloud.user.auth.mapper;

import com.stayrascal.cloud.common.mapper.DefaultMapper;
import com.stayrascal.cloud.user.auth.domain.entity.Role;
import com.stayrascal.cloud.user.auth.domain.entity.RolePermission;
import com.stayrascal.cloud.user.auth.infrastructure.persistence.po.RolePermissionPo;
import com.stayrascal.cloud.user.auth.infrastructure.persistence.po.RolePo;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class RolePoMapper extends DefaultMapper {
    public RolePoMapper() {
        register(RolePo.class, Role.class);
        register(RolePermissionPo.class, RolePermission.class);
    }

    public Role roleFromPo(RolePo rolePo) {
        Role role = map(rolePo, Role.class);
        role.setRolePermissions(rolePo.getPermissions().stream()
                .map(this::rolePermissionFromPo).collect(Collectors.toList()));
        return role;
    }

    public RolePermission rolePermissionFromPo(RolePermissionPo rolePermissionPo) {
        return map(rolePermissionPo, RolePermission.class);
    }

    public RolePo roleToPo(Role role) {
        RolePo rolePo = map(role, RolePo.class);
        rolePo.setPermissions(role.getRolePermissions().stream()
                .map(this::rolePermissionToPo).collect(Collectors.toList()));
        return rolePo;
    }

    public RolePermissionPo rolePermissionToPo(RolePermission rolePermission) {
        return map(rolePermission, RolePermissionPo.class);
    }
}
