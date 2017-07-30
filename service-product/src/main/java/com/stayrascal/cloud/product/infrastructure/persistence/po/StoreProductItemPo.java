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
@Table(name = "STORE_PRODUCT_ITEM")
public class StoreProductItemPo extends BasePo {

    @Column(name = "store_product_id", length = 64, nullable = false, updatable = false)
    private String storeProductId;

    @Column(name = "item_id", length = 64)
    private String itemId;

    @Column(name = "store_id", length = 64)
    private String storeId;

    @Column(name = "quantity", length = 32, nullable = false)
    private Integer quantity;

    @Column(name = "price", length = 32, nullable = false)
    private BigDecimal price;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private List<OptionPairPo> optionPairs;

    public String getStoreProductId() {
        return storeProductId;
    }

    public void setStoreProductId(String storeProductId) {
        this.storeProductId = storeProductId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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
