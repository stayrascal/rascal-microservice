package com.stayrascal.cloud.product.contract.command;

import com.stayrascal.cloud.common.contract.enumeration.CommonStatus;

import java.util.List;
import java.util.Map;

public class UpdateProductCommand {
    private String name;
    private String description;
    private String thumbnail;
    private CommonStatus status;
    private List<CreateProductItemCommand> addItemCommands;
    private Map<String, UpdateProductItemCommand> updateItemCommands;
    private List<String> deleteItemCommands;

    public UpdateProductCommand() {
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

    public CommonStatus getStatus() {
        return this.status;
    }

    public void setStatus(CommonStatus status) {
        this.status = status;
    }

    public List<CreateProductItemCommand> getAddItemCommands() {
        return addItemCommands;
    }

    public void setAddItemCommands(List<CreateProductItemCommand> addItemCommands) {
        this.addItemCommands = addItemCommands;
    }

    public Map<String, UpdateProductItemCommand> getUpdateItemCommands() {
        return updateItemCommands;
    }

    public void setUpdateItemCommands(Map<String, UpdateProductItemCommand> updateItemCommands) {
        this.updateItemCommands = updateItemCommands;
    }

    public List<String> getDeleteItemCommands() {
        return deleteItemCommands;
    }

    public void setDeleteItemCommands(List<String> deleteItemCommands) {
        this.deleteItemCommands = deleteItemCommands;
    }
}
