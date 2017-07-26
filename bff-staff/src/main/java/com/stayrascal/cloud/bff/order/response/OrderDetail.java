package com.stayrascal.cloud.bff.order.response;

import com.stayrascal.cloud.order.contract.dto.OrderItemDto;

import java.util.List;

public class OrderDetail extends OrderCommon {
    private String note;
    private List<OrderItem> items;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public static final class OrderItem extends OrderItemDto {
        private String productThumbnail;

        public String getProductThumbnail() {
            return productThumbnail;
        }

        public void setProductThumbnail(String productThumbnail) {
            this.productThumbnail = productThumbnail;
        }
    }
}
