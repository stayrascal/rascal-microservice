package com.stayrascal.cloud.store.contract.dto;

public class StoreWithDistance extends StoreDto {
    private Double distance;

    public StoreWithDistance() {
    }

    public Double getDistance() {
        return this.distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }
}
