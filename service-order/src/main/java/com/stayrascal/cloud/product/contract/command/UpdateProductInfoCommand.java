package com.stayrascal.cloud.product.contract.command;

import com.stayrascal.cloud.common.enumeration.CommonStatus;

public class UpdateProductInfoCommand {
    private String name;
    private String description;
    private String thumbnail;
    private CommonStatus status;

    public UpdateProductInfoCommand() {
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
}
