package com.stayrascal.cloud.mapping.infrastructure.persistence.po;

import org.joda.time.DateTime;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "STAFF_ADDRESS_MAPPING")
public class StaffAddressMappingPo {
    @Id
    @Column(name = "user_id", length = 64, nullable = false, updatable = false)
    private String userId;

    @Column(name = "address_id")
    private Long addressId;

    @Column(
            name = "time_created",
            nullable = false,
            updatable = false
    )
    private Date timeCreated;

    public StaffAddressMappingPo() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Date getTimeCreated() {
        return timeCreated;
    }

    public StaffAddressMappingPo(String userId, Long addressId) {
        this.userId = userId;
        this.addressId = addressId;
        this.timeCreated = DateTime.now().toDate();
    }
}
