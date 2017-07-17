package com.stayrascal.cloud.order.contract.command;

import com.stayrascal.cloud.order.contract.enumeration.OrderStatus;

public class UpdateOrderCommand {
    private String note;

    private OrderStatus status;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
