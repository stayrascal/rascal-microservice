package com.stayrascal.cloud.product.infrastructure.persistence.po;

import com.stayrascal.cloud.common.jpa.BasePo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "OPTION_VALUE")
public class OptionValuePo extends BasePo {

    @Column(name = "value", length = 32)
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
