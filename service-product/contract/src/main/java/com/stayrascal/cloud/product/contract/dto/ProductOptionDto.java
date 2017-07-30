package com.stayrascal.cloud.product.contract.dto;

import java.util.List;

public class ProductOptionDto {
    private String id;
    private String name;
    private List<OptionValueDto> values;

    public ProductOptionDto() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<OptionValueDto> getValues() {
        return this.values;
    }

    public void setValues(List<OptionValueDto> values) {
        this.values = values;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
