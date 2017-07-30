package com.stayrascal.cloud.product.contract.command;

import com.stayrascal.cloud.product.contract.dto.OptionPairDto;

import java.math.BigDecimal;
import java.util.List;

public class CreateProductItemCommand {
    private List<OptionPairDto> optionPairDtos;
    private BigDecimal price;

    public CreateProductItemCommand() {
    }

    public List<OptionPairDto> getOptionPairDtos() {
        return this.optionPairDtos;
    }

    public void setOptionPairDtos(List<OptionPairDto> optionPairDtos) {
        this.optionPairDtos = optionPairDtos;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
