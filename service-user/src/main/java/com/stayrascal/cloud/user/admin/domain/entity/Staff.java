package com.stayrascal.cloud.user.admin.domain.entity;

import com.stayrascal.cloud.user.admin.domain.vo.StaffAuthorization;

import com.stayrscal.cloud.user.admin.contract.StaffStatus;

import java.util.Date;
import java.util.List;

public class Staff {
    private String id;

    private String mobile;

    private String email;

    private String name;

    private List<StaffAuthorization> authorizations;

    private Date timeCreated;

    private StaffStatus status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public List<StaffAuthorization> getAuthorizations() {
        return authorizations;
    }

    public void setAuthorizations(List<StaffAuthorization> authorizations) {
        this.authorizations = authorizations;
    }

    public Date getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }

    public StaffStatus getStatus() {
        return status;
    }

    public void setStatus(StaffStatus status) {
        this.status = status;
    }
}
