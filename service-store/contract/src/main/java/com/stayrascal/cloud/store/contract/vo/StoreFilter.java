package com.stayrascal.cloud.store.contract.vo;

import com.stayrascal.cloud.common.util.TimeRange;

public class StoreFilter {
    private AddressFilter addressFilter;
    private DistanceFilter distanceFilter;
    private TimeRange timeRange;
    private String name;

    public StoreFilter(AddressFilter addressFilter, DistanceFilter distanceFilter, TimeRange timeRange, String name) {
        this.addressFilter = addressFilter;
        this.distanceFilter = distanceFilter;
        this.timeRange = timeRange;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AddressFilter getAddressFilter() {
        return this.addressFilter;
    }

    public void setAddressFilter(AddressFilter addressFilter) {
        this.addressFilter = addressFilter;
    }

    public DistanceFilter getDistanceFilter() {
        return this.distanceFilter;
    }

    public void setDistanceFilter(DistanceFilter distanceFilter) {
        this.distanceFilter = distanceFilter;
    }

    public TimeRange getTimeRange() {
        return this.timeRange;
    }

    public void setTimeRange(TimeRange timeRange) {
        this.timeRange = timeRange;
    }
}
