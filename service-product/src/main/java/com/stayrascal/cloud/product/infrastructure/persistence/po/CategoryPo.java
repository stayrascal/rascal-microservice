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
@Table(name = "CATEGORY")
public class CategoryPo extends BasePo {
    @Column(name = "name", length = 32)
    private String name;

    @Column(name = "index", length = 16)
    private int index;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private List<ProductOptionPo> options;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public List<ProductOptionPo> getOptions() {
        return options;
    }

    public void setOptions(List<ProductOptionPo> options) {
        this.options = options;
    }
}
