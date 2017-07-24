package com.stayrascal.cloud.store.contract.vo;

public class AddressFilter {
    private Long provinceId;
    private Long cityId;

    public AddressFilter(Long provinceId, Long cityId) {
        this.provinceId = provinceId;
        this.cityId = cityId;
    }

    public Long getProvinceId() {
        return this.provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public Long getCityId() {
        return this.cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }
}
