package com.stayrascal.cloud.product.contract.command;

import com.stayrascal.cloud.product.contract.dto.OptionValueDto;

import java.util.List;

public class UpdateProductOptionCommand {
    private String name;
    private List<OptionValueDto> optionValues;

    public UpdateProductOptionCommand() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<OptionValueDto> getOptionValues() {
        return this.optionValues;
    }

    public void setOptionValues(List<OptionValueDto> optionValues) {
        this.optionValues = optionValues;
    }
}
