package com.stayrascal.cloud.product.contract.dto;

public class OptionPairDto {
    private String valueId;
    private String name;
    private String value;

    public OptionPairDto() {
    }

    public OptionPairDto(String valueId, String name, String value) {
        this.valueId = valueId;
        this.name = name;
        this.value = value;
    }

    public String getValueId() {
        return this.valueId;
    }

    public void setValueId(String valueId) {
        this.valueId = valueId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
