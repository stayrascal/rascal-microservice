package com.stayrascal.cloud.user.auth.facade;

import com.stayrascal.cloud.common.constant.ErrorCode;
import com.stayrascal.cloud.common.jersey.exception.NotFoundException;
import com.stayrascal.cloud.user.auth.domain.entity.Role;
import com.stayrascal.cloud.user.auth.domain.entity.RolePermission;
import com.stayrascal.cloud.user.auth.domain.factory.RoleFactory;
import com.stayrascal.cloud.user.auth.domain.repository.RoleRepository;
import com.stayrascal.cloud.user.auth.mapper.RoleDtoMapper;
import com.stayrascal.cloud.common.contract.enumeration.SortType;

import com.stayrscal.cloud.user.auth.contract.command.CreateRoleCommand;
import com.stayrscal.cloud.user.auth.contract.command.CreateRolePermissionCommand;
import com.stayrscal.cloud.user.auth.contract.dto.RoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Component
@Transactional
public class RoleFacade {
    private final RoleRepository roleRepository;
    private final RoleDtoMapper roleDtoMapper;
    private final RoleFactory roleFactory;

    @Autowired
    public RoleFacade(RoleRepository roleRepository,
                      RoleDtoMapper roleDtoMapper,
                      RoleFactory roleFactory) {
        this.roleRepository = roleRepository;
        this.roleDtoMapper = roleDtoMapper;
        this.roleFactory = roleFactory;
    }

    public RoleDto getRole(String roleId) {
        Role role = getRoleEntity(roleId);
        return roleDtoMapper.roleToDto(role);
    }

    private Role getRoleEntity(String roleId) {
        return roleRepository.ofId(roleId).orElseThrow(() -> new NotFoundException(ErrorCode.INTERNAL_ERROR,
                "Role of id {} not found!", roleId));
    }

    public String createRole(CreateRoleCommand command) {
        Role role = roleFactory.create(command);
        roleRepository.insert(role);
        return role.getId();
    }

    public Long countRoles(String name) {
        return roleRepository.countRoles(name);
    }

    public List<RoleDto> listRoles(Integer pageSize, Integer pageIndex, SortType sortType, String sortBy, String name) {
        List<Role> roles = roleRepository.listRoles(pageSize, pageIndex, sortType, sortBy, name);
        return roles.stream().map(roleDtoMapper::roleToDto).collect(Collectors.toList());
    }

    public Long createRolePermission(String roleId, CreateRolePermissionCommand command) {
        Role role = getRoleEntity(roleId);
        RolePermission rolePermission = role.addPermission(command.getPermissionType());
        Long permissionId = roleRepository.insertPermission(role, rolePermission);
        return permissionId;
    }

    public void deleteRolePermission(String roleId, String permissionId) {
        Role role = getRoleEntity(roleId);
        role.removePermission(permissionId);
        roleRepository.update(role);
    }
}
