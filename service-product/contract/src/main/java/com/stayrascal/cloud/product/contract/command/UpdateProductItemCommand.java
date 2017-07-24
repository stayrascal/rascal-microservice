package com.stayrascal.cloud.product.contract.command;

import java.math.BigDecimal;

public class UpdateProductItemCommand {
    private BigDecimal price;

    public UpdateProductItemCommand() {
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
