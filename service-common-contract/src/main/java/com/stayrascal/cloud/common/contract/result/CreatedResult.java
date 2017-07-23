package com.stayrascal.cloud.common.contract.result;

public class CreatedResult {
    private String id;

    public CreatedResult() {
    }

    public CreatedResult(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
