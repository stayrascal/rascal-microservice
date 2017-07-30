package com.stayrascal.cloud.product.domain.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ProductItem {
    private String id;
    private String productId;
    private BigDecimal price;
    private List<OptionPair> optionPairDtos;
    private Date timeCreated;

    public Date getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public List<OptionPair> getOptionPairDtos() {
        return optionPairDtos;
    }

    public void setOptionPairDtos(List<OptionPair> optionPairDtos) {
        this.optionPairDtos = optionPairDtos;
    }
}
