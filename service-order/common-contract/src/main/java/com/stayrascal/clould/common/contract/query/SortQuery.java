package com.stayrascal.clould.common.contract.query;

import com.stayrascal.cloud.common.enumeration.SortType;

public class SortQuery {
    private SortType sortType;
    private String sortBy;
    private Integer pageSize;
    private Integer pageIndex;

    public SortQuery(SortType sortType, String sortBy, Integer pageSize, Integer pageIndex) {
        this.sortType = sortType;
        this.sortBy = sortBy;
        this.pageSize = pageSize;
        this.pageIndex = pageIndex;
    }

    public SortType getSortType() {
        return sortType;
    }

    public void setSortType(SortType sortType) {
        this.sortType = sortType;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
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
}
