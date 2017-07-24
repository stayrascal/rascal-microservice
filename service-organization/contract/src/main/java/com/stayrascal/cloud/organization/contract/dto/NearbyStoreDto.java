package com.stayrascal.cloud.organization.contract.dto;

public class NearbyStoreDto {
    private Double distance;

    private StoreDto storeDto;

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public StoreDto getStoreDto() {
        return storeDto;
    }

    public void setStoreDto(StoreDto storeDto) {
        this.storeDto = storeDto;
    }
}
