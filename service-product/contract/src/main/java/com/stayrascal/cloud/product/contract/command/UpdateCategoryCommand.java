package com.stayrascal.cloud.product.contract.command;

import com.stayrascal.cloud.product.contract.dto.OptionDto;

import java.util.List;

public class UpdateCategoryCommand {
    private String name;
    private Integer index;
    private List<OptionDto> options;

    public UpdateCategoryCommand() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIndex() {
        return this.index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public List<OptionDto> getOptions() {
        return options;
    }

    public void setOptions(List<OptionDto> options) {
        this.options = options;
    }
}
