package com.stayrascal.cloud.bff.order.request;

import com.stayrascal.cloud.order.contract.command.UpdateOrderCommand;

public class UpdateOrderWithOrderIdCommand extends UpdateOrderCommand {
    private String orderId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
