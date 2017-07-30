package com.stayrascal.cloud.product.infrastructure.persistence.po;

import com.stayrascal.cloud.common.jpa.BasePo;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT_OPTION")
public class ProductOptionPo extends BasePo {
    @Column(name = "name", length = 32)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private List<OptionValuePo> values;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<OptionValuePo> getValues() {
        return values;
    }

    public void setValues(List<OptionValuePo> values) {
        this.values = values;
    }
}
