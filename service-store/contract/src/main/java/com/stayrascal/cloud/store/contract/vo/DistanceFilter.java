package com.stayrascal.cloud.store.contract.vo;

import com.github.wenhao.geohash.GeoHash;

public class DistanceFilter {
    private Double longitude;
    private Double latitude;
    private Integer distance;

    public DistanceFilter() {
    }

    public DistanceFilter(Double longitude, Double latitude, Integer distance) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.distance = distance;
    }

    public boolean inDistanceRange(Long geoHash) {
        return !this.valid() || GeoHash.fromLong(geoHash.longValue()).distance(this.latitude.doubleValue(),
                this.longitude.doubleValue()) < (double) this.distance.intValue();
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Integer getDistance() {
        return this.distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public boolean valid() {
        return this.longitude != null && this.latitude != null && this.distance != null;
    }
}
