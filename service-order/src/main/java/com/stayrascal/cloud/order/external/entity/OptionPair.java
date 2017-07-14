package com.stayrascal.cloud.order.external.entity;

public class OptionPair {
    private String name;

    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static OptionPair mapFrom(com.stayrascal.cloud.product.contract.dto.OptionPair optionPair) {
        OptionPair pair = new OptionPair();
        pair.setName(optionPair.getName());
        pair.setValue(optionPair.getValue());
        return pair;
    }
}
