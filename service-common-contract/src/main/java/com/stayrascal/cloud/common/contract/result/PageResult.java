package com.stayrascal.cloud.common.contract.result;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.List;

public class PageResult {
    private Long totalCount;
    private Integer pageSize;
    private Integer pageIndex;
    @JsonTypeInfo(
            use = JsonTypeInfo.Id.CLASS,
            include = JsonTypeInfo.As.PROPERTY
    )
    private List items;

    public PageResult() {
    }

    public PageResult(Long totalCount, Integer pageSize, Integer pageIndex, List items) {
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.pageIndex = pageIndex;
        this.items = items;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public void setItems(List items) {
        this.items = items;
    }

    public Long getTotalCount() {
        return this.totalCount;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public Integer getPageIndex() {
        return this.pageIndex;
    }

    public List getItems() {
        return this.items;
    }
}
