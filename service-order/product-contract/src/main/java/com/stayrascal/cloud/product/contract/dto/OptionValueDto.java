package com.stayrascal.cloud.product.contract.dto;

public class OptionValueDto {
    private String id;
    private String value;

    public OptionValueDto() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
