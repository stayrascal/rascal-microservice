package com.stayrascal.cloud.bff.store.response;

import com.stayrascal.cloud.address.contract.dto.AddressDto;
import com.stayrascal.cloud.common.contract.enumeration.CommonStatus;

import java.util.Date;

public class StoreResponse {
    private String id;

    private String name;

    private String shopTime;

    private String closingTime;

    private AddressDto province;

    private AddressDto city;

    private AddressDto district;

    private String address;

    private Double longitude;

    private Double latitude;

    private String contactNumber;

    private CommonStatus status;

    private Date timeCreated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShopTime() {
        return shopTime;
    }

    public void setShopTime(String shopTime) {
        this.shopTime = shopTime;
    }

    public String getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(String closingTime) {
        this.closingTime = closingTime;
    }

    public AddressDto getProvince() {
        return province;
    }

    public void setProvince(AddressDto province) {
        this.province = province;
    }

    public AddressDto getCity() {
        return city;
    }

    public void setCity(AddressDto city) {
        this.city = city;
    }

    public AddressDto getDistrict() {
        return district;
    }

    public void setDistrict(AddressDto district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public CommonStatus getStatus() {
        return status;
    }

    public void setStatus(CommonStatus status) {
        this.status = status;
    }

    public Date getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }
}
