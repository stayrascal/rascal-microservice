package com.stayrscal.cloud.user.admin.contract.command;

import com.stayrascal.cloud.common.contract.auth.Authorization;

import java.util.List;

public class CreateStaffCommand {
    private String mobile;

    private String email;

    private String name;

    private List<Authorization> authorizations;

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

    public List<Authorization> getAuthorizations() {
        return authorizations;
    }

    public void setAuthorizations(List<Authorization> authorizations) {
        this.authorizations = authorizations;
    }
}
