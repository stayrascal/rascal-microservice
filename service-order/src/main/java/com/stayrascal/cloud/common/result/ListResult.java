package com.stayrascal.cloud.common.result;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.List;

public class ListResult {
    private Integer totalCount;
    @JsonTypeInfo(
            use = JsonTypeInfo.Id.CLASS,
            include = JsonTypeInfo.As.PROPERTY
    )
    private List items;

    public ListResult() {
    }

    public ListResult(List items) {
        this.items = items;
        this.totalCount = Integer.valueOf(items.size());
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public void setItems(List items) {
        this.items = items;
    }

    public Integer getTotalCount() {
        return this.totalCount;
    }

    public List getItems() {
        return this.items;
    }
}
