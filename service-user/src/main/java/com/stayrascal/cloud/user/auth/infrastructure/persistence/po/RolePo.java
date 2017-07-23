package com.stayrascal.cloud.user.auth.infrastructure.persistence.po;

import com.stayrascal.cloud.common.jpa.BasePo;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ROLE")
public class RolePo extends BasePo {
    @Column(name = "name", length = 64, nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private List<RolePermissionPo> permissions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RolePermissionPo> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<RolePermissionPo> permissions) {
        this.permissions = permissions;
    }
}
