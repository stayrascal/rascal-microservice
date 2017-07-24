package com.stayrascal.cloud.product.contract.command;

import java.util.List;

public class CreateCategoryCommand {
    private String name;
    private List<CreateProductOptionCommand> optionCommands;

    public CreateCategoryCommand() {
    }

    public CreateCategoryCommand(String name, List<CreateProductOptionCommand> optionCommands) {
        this.name = name;
        this.optionCommands = optionCommands;
    }

    public CreateCategoryCommand(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CreateProductOptionCommand> getOptionCommands() {
        return optionCommands;
    }

    public void setOptionCommands(List<CreateProductOptionCommand> optionCommands) {
        this.optionCommands = optionCommands;
    }
}
