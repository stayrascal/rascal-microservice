package com.stayrascal.cloud.common.lib.jpa;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
public class BasePo {
    @Id
    @Column(
            name = "id",
            length = 64,
            nullable = false,
            updatable = false
    )
    private String id;
    @Column(
            name = "time_created",
            nullable = false,
            updatable = false
    )
    private Date timeCreated;

    public BasePo() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getTimeCreated() {
        return this.timeCreated;
    }

    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }
}
