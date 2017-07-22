package com.stayrascal.cloud.product.contract.command;

import java.util.Set;

public class CreateProductOptionCommand {
    private String name;
    private Set<String> values;

    public CreateProductOptionCommand() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getValues() {
        return this.values;
    }

    public void setValues(Set<String> values) {
        this.values = values;
    }
}
