package com.stayrascal.cloud.order.infrastructure.persistence.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PICKUP_CODE_SEED")
public class PickupCodeSeedPo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code_differentiator", length = 64, nullable = false, updatable = false)
    private String codeDifferentiator;

    @Column(name = "next_code", nullable = false)
    private Integer nextCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeDifferentiator() {
        return codeDifferentiator;
    }

    public void setCodeDifferentiator(String codeDifferentiator) {
        this.codeDifferentiator = codeDifferentiator;
    }

    public Integer getNextCode() {
        return nextCode;
    }

    public void setNextCode(Integer nextCode) {
        this.nextCode = nextCode;
    }
}
