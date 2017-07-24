package com.stayrascal.cloud.product.contract.dto;

import java.math.BigDecimal;
import java.util.List;

public class ItemDto {
    private String id;
    private String productId;
    private BigDecimal price;
    private List<OptionPair> optionPairs;

    public ItemDto() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<OptionPair> getOptionPairs() {
        return this.optionPairs;
    }

    public void setOptionPairs(List<OptionPair> optionPairs) {
        this.optionPairs = optionPairs;
    }
}
