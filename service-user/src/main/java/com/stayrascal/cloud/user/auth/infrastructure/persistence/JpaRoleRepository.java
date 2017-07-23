package com.stayrascal.cloud.user.auth.infrastructure.persistence;

import com.stayrascal.cloud.user.auth.domain.entity.Role;
import com.stayrascal.cloud.user.auth.domain.entity.RolePermission;
import com.stayrascal.cloud.user.auth.domain.repository.RoleRepository;
import com.stayrascal.cloud.user.auth.infrastructure.persistence.po.RolePermissionPo;
import com.stayrascal.cloud.user.auth.infrastructure.persistence.po.RolePo;
import com.stayrascal.cloud.user.auth.mapper.RolePoMapper;
import com.stayrascal.clould.common.contract.enumeration.SortType;

import com.exmertec.yaz.BaseDao;
import com.exmertec.yaz.core.OrderType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;

@Component
public class JpaRoleRepository implements RoleRepository {
    private final BaseDao<RolePo> roleDao;
    private final RolePoMapper rolePoMapper;
    private final BaseDao<RolePermissionPo> rolePermissionDao;

    @Autowired
    public JpaRoleRepository(EntityManager entityManager, RolePoMapper rolePoMapper) {
        this.roleDao = new BaseDao<>(entityManager, RolePo.class);
        this.rolePermissionDao = new BaseDao<>(entityManager, RolePermissionPo.class);
        this.rolePoMapper = rolePoMapper;
    }

    @Override
    public Optional<Role> ofId(String id) {
        RolePo rolePo = roleDao.idEquals(id).querySingle();
        return Optional.ofNullable(rolePo).map(rolePoMapper::roleFromPo);
    }

    @Override
    public String insert(Role role) {
        RolePo rolePo = rolePoMapper.roleToPo(role);
        roleDao.save(rolePo);
        return rolePo.getId();
    }

    @Override
    public Role update(Role role) {
        RolePo rolePo = rolePoMapper.roleToPo(role);
        roleDao.update(rolePo);
        return role;
    }

    @Override
    public Long countRoles(String name) {
        return roleDao.where(BaseDao.field("name").like(name)).count();
    }

    @Override
    public List<Role> listRoles(Integer pageSize, Integer pageIndex, SortType sortType, String sortBy, String name) {
        List<RolePo> rolePos = roleDao.where(BaseDao.field("name").like(name))
                .orderBy(sortType == SortType.ASC ? OrderType.ASCENDING : OrderType.DESCENDING)
                .queryPage(pageSize, pageIndex);

        return rolePos.stream().map(rolePoMapper::roleFromPo).collect(Collectors.toList());
    }

    @Override
    public Long insertPermission(Role role, RolePermission rolePermission) {
        RolePermissionPo rolePermissionPo = rolePoMapper.rolePermissionToPo(rolePermission);
        rolePermissionPo.setRoleId(role.getId());
        rolePermissionDao.save(rolePermissionPo);
        return rolePermissionPo.getId();
    }
}
