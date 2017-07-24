package com.stayrascal.cloud.product.contract.command;

import com.stayrascal.cloud.product.contract.dto.OptionPair;

import java.math.BigDecimal;
import java.util.List;

public class CreateProductItemCommand {
    private List<OptionPair> optionPairs;
    private BigDecimal price;

    public CreateProductItemCommand() {
    }

    public List<OptionPair> getOptionPairs() {
        return this.optionPairs;
    }

    public void setOptionPairs(List<OptionPair> optionPairs) {
        this.optionPairs = optionPairs;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
