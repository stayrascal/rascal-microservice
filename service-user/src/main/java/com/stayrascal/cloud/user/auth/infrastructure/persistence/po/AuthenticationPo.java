package com.stayrascal.cloud.user.auth.infrastructure.persistence.po;

import com.stayrascal.cloud.common.jpa.BasePo;
import com.stayrascal.clould.common.contract.auth.IdentityType;
import com.stayrscal.cloud.user.auth.contract.AuthenticationType;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;


@Entity
@Table(name = "AUTHENTICATION")
public class AuthenticationPo extends BasePo {
    @Enumerated(EnumType.STRING)
    @Column(name = "identity_type", length = 32, updatable = false, nullable = false)
    private IdentityType identityType;

    @Column(name = "identity_id", length = 64, updatable = false, nullable = false)
    private String identityId;

    @Enumerated(EnumType.STRING)
    @Column(name = "authentication_type", length = 32, updatable = false, nullable = false)
    private AuthenticationType authenticationType;

    @Column(name = "authentication_name", length = 64, updatable = false, nullable = false)
    private String authenticationName;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "authentication_id")
    private List<AuthenticationKeyPo> authenticationKeys;

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

    public AuthenticationType getAuthenticationType() {
        return authenticationType;
    }

    public void setAuthenticationType(AuthenticationType authenticationType) {
        this.authenticationType = authenticationType;
    }

    public String getAuthenticationName() {
        return authenticationName;
    }

    public void setAuthenticationName(String authenticationName) {
        this.authenticationName = authenticationName;
    }

    public List<AuthenticationKeyPo> getAuthenticationKeys() {
        return authenticationKeys;
    }

    public void setAuthenticationKeys(List<AuthenticationKeyPo> authenticationKeys) {
        this.authenticationKeys = authenticationKeys;
    }
}