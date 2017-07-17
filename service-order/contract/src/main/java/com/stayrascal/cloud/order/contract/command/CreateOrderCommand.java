package com.stayrascal.cloud.order.contract.command;

import com.stayrascal.cloud.order.contract.enumeration.DeliveryMethod;

import java.math.BigDecimal;
import java.util.List;

public class CreateOrderCommand {
    private String userId;
    private String storeId;
    private String note;
    private DeliveryMethod deliveryMethod;
    private List<CreateOrderItemCommand> items;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public DeliveryMethod getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(DeliveryMethod deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public List<CreateOrderItemCommand> getItems() {
        return items;
    }

    public void setItems(List<CreateOrderItemCommand> items) {
        this.items = items;
    }

    public static class CreateOrderItemCommand {
        private String storeProductId;
        private String storeItemId;
        private String storeProductName;
        private String storeItemName;
        private Integer quantity;
        private BigDecimal price;

        public String getStoreProductId() {
            return storeProductId;
        }

        public void setStoreProductId(String storeProductId) {
            this.storeProductId = storeProductId;
        }

        public String getStoreItemId() {
            return storeItemId;
        }

        public void setStoreItemId(String storeItemId) {
            this.storeItemId = storeItemId;
        }

        public String getStoreProductName() {
            return storeProductName;
        }

        public void setStoreProductName(String storeProductName) {
            this.storeProductName = storeProductName;
        }

        public String getStoreItemName() {
            return storeItemName;
        }

        public void setStoreItemName(String storeItemName) {
            this.storeItemName = storeItemName;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }
    }
}
