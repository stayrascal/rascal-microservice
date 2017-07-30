package com.stayrascal.cloud.product.contract.command;

import com.stayrascal.cloud.product.contract.dto.ProductOptionDto;

import java.util.List;

public class CreateProductCommand {
    private String name;
    private String description;
    private String thumbnail;
    private String categoryId;
    private List<ProductOptionDto> options;
    private List<CreateProductItemCommand> createProductItemCommands;

    public CreateProductCommand() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return this.thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public List<ProductOptionDto> getOptions() {
        return this.options;
    }

    public void setOptions(List<ProductOptionDto> options) {
        this.options = options;
    }

    public List<CreateProductItemCommand> getCreateProductItemCommands() {
        return createProductItemCommands;
    }

    public void setCreateProductItemCommands(List<CreateProductItemCommand> createProductItemCommands) {
        this.createProductItemCommands = createProductItemCommands;
    }
}
