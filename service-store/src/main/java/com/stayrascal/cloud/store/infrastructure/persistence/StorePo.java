package com.stayrascal.cloud.store.infrastructure.persistence;

import com.stayrascal.cloud.common.contract.enumeration.CommonStatus;
import com.stayrascal.cloud.common.jpa.BasePo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "STORE")
public class StorePo extends BasePo {
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 32)
    private CommonStatus status;

    public CommonStatus getStatus() {
        return status;
    }

    public void setStatus(CommonStatus status) {
        this.status = status;
    }
}
