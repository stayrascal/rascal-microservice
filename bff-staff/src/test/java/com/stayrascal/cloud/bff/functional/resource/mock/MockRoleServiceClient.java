package com.stayrascal.cloud.bff.functional.resource.mock;

import com.stayrascal.cloud.common.contract.enumeration.SortType;
import com.stayrascal.cloud.common.contract.result.CreatedResult;
import com.stayrascal.cloud.common.contract.result.PageResult;
import com.stayrascal.cloud.user.auth.contract.client.RoleServiceClient;
import com.stayrascal.cloud.user.auth.contract.command.CreateRoleCommand;
import com.stayrascal.cloud.user.auth.contract.command.CreateRolePermissionCommand;
import com.stayrascal.cloud.user.auth.contract.dto.RoleDto;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MockRoleServiceClient implements RoleServiceClient {
    @Override
    public RoleDto getRole(String roleId) {
        return null;
    }

    @Override
    public CreatedResult createRole(CreateRoleCommand command) {
        return null;
    }

    @Override
    public PageResult listRoles(SortType sortType, String sortBy, Integer pageSize, Integer pageIndex, Map<String, String> queryMap) {
        return null;
    }

    @Override
    public CreatedResult createRolePermission(String roleId, CreateRolePermissionCommand command) {
        return null;
    }

    @Override
    public void deleteRolePermission(String roleId, String permissionId) {

    }
}
