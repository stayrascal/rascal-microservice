package com.stayrascal.cloud.user.admin.infrastructure.persistence.po;

import com.stayrascal.cloud.common.jpa.BasePo;

import com.stayrscal.cloud.user.admin.contract.StaffStatus;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "STAFF")
public class StaffPo extends BasePo {
    @Column(name = "mobile", length = 16)
    private String mobile;

    @Column(name = "email", length = 64)
    private String email;

    @Column(name = "name", length = 64)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id")
    private List<StaffAuthorizationPo> authorizations;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 32)
    private StaffStatus status;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<StaffAuthorizationPo> getAuthorizations() {
        return authorizations;
    }

    public void setAuthorizations(List<StaffAuthorizationPo> authorizations) {
        this.authorizations = authorizations;
    }

    public StaffStatus getStatus() {
        return status;
    }

    public void setStatus(StaffStatus status) {
        this.status = status;
    }
}

