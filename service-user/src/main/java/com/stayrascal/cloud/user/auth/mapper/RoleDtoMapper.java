package com.stayrascal.cloud.user.auth.mapper;

import com.stayrascal.cloud.common.mapper.DefaultMapper;
import com.stayrascal.cloud.user.auth.domain.entity.Role;
import com.stayrascal.cloud.user.auth.domain.entity.RolePermission;
import com.stayrscal.cloud.user.auth.contract.dto.RoleDto;
import com.stayrscal.cloud.user.auth.contract.dto.RoleDto.RolePermissionDto;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class RoleDtoMapper extends DefaultMapper {
    public RoleDtoMapper() {
        register(RoleDto.class, Role.class);
        register(RolePermissionDto.class, RolePermission.class);
    }

    public Role roleFromDto(RoleDto roleDto) {
        Role role = map(roleDto, Role.class);
        role.setRolePermissions(roleDto.getPermissions().stream()
                .map(this::rolePermissionFromDto).collect(Collectors.toList()));
        return role;
    }

    private RolePermission rolePermissionFromDto(RolePermissionDto rolePermissionDto) {
        return map(rolePermissionDto, RolePermission.class);
    }

    public RoleDto roleToDto(Role role) {
        RoleDto roleDto = map(role, RoleDto.class);
        roleDto.setPermissions(role.getRolePermissions().stream()
                .map(this::rolePermissionToDto).collect(Collectors.toList()));
        return roleDto;
    }

    private RolePermissionDto rolePermissionToDto(RolePermission rolePermission) {
        return map(rolePermission, RolePermissionDto.class);
    }
}
