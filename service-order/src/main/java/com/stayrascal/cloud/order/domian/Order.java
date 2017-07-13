package com.stayrascal.cloud.order.domian;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Order {

    public static final Logger LOGGER = LoggerFactory.getLogger(Order.class);

    private String id;
    private String userId;
    private String storeId;
    private String note;
    private String pickupCode;
    private String transactionId;
}
