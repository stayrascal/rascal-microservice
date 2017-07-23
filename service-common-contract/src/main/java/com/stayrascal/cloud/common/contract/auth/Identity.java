package com.stayrascal.cloud.common.contract.auth;

import java.util.ArrayList;
import java.util.List;

public class Identity {
    private IdentityType identityType;
    private String identityId;
    private List<Authorization> authorizations;

    public Identity() {
    }

    public Identity(IdentityType identityType, String identityId) {
        this.identityType = identityType;
        this.identityId = identityId;
        this.authorizations = new ArrayList<>(0);
    }

    public Identity(IdentityType identityType, String identityId, List<Authorization> authorizations) {
        this.identityType = identityType;
        this.identityId = identityId;
        this.authorizations = authorizations;
    }

    public IdentityType getIdentityType() {
        return identityType;
    }

    public void setIdentityType(IdentityType identityType) {
        this.identityType = identityType;
    }

    public String getIdentityId() {
        return identityId;
    }

    public void setIdentityId(String identityId) {
        this.identityId = identityId;
    }

    public List<Authorization> getAuthorizations() {
        return authorizations;
    }

    public void setAuthorizations(List<Authorization> authorizations) {
        this.authorizations = authorizations;
    }
}
