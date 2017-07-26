package com.stayrascal.cloud.bff.order.response;

public class ListedOrder extends OrderCommon {
    private int totalItems;

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }
}
