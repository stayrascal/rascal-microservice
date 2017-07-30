package com.stayrascal.cloud.product.infrastructure.persistence.po;

import com.stayrascal.cloud.common.jpa.BasePo;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT_ITEM")
public class ProductItemPo extends BasePo {

    @Column(name = "product_id", length = 64, nullable = false, updatable = false)
    private String productId;

    @Column(name = "price")
    private BigDecimal price;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private List<OptionPairPo> optionPairs;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<OptionPairPo> getOptionPairs() {
        return optionPairs;
    }

    public void setOptionPairs(List<OptionPairPo> optionPairs) {
        this.optionPairs = optionPairs;
    }
}
