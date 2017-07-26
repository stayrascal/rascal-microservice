package com.stayrascal.cloud.bff.common;

import java.util.List;

public class PageResponse {
    private Long totalCount;

    private Integer pageSize;

    private Integer pageIndex;

    private List items;

    public PageResponse() {
    }

    public PageResponse(Long totalCount, Integer pageSize, Integer pageIndex, List items) {
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.pageIndex = pageIndex;
        this.items = items;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public List getItems() {
        return items;
    }

    public void setItems(List items) {
        this.items = items;
    }
}
