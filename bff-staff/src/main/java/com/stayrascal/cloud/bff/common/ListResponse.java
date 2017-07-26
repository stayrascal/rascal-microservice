package com.stayrascal.cloud.bff.common;

import java.util.List;

public class ListResponse {
    private Integer totalCount;

    private List items;

    public ListResponse() {
    }

    public ListResponse(List items) {
        this.totalCount = items.size();
        this.items = items;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List getItems() {
        return items;
    }

    public void setItems(List items) {
        this.items = items;
    }
}
