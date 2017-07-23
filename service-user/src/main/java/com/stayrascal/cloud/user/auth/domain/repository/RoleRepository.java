package com.stayrascal.cloud.user.auth.domain.repository;

import com.stayrascal.cloud.common.ddd.Repository;
import com.stayrascal.cloud.user.auth.domain.entity.Role;
import com.stayrascal.cloud.user.auth.domain.entity.RolePermission;
import com.stayrascal.cloud.common.contract.enumeration.SortType;

import java.util.List;

public interface RoleRepository extends Repository<Role, String> {
    Long countRoles(String name);

    List<Role> listRoles(Integer pageSize, Integer pageIndex, SortType sortType, String sortBy, String name);

    Long insertPermission(Role role, RolePermission rolePermission);
}
