package com.stayrascal.cloud.product.contract.command;

import java.math.BigDecimal;

public class UpdateStoreProductItemCommand {
    private Integer quantity;
    private BigDecimal price;

    public UpdateStoreProductItemCommand() {
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
