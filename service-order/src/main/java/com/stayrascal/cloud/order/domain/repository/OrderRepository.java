package com.stayrascal.cloud.order.domain.repository;

import com.stayrascal.cloud.common.ddd.Repository;
import com.stayrascal.cloud.common.util.TimeRange;
import com.stayrascal.cloud.order.contract.enumeration.OrderStatus;
import com.stayrascal.cloud.order.domain.entity.Order;
import com.stayrascal.clould.common.contract.query.SortQuery;

import java.util.List;

public interface OrderRepository extends Repository<Order, String> {
    long countOrders(String id, String storeId, OrderStatus status, String pickupCode, TimeRange placedTimeRange,
                     String userId);

    List<Order> listOrders(SortQuery sortQuery, String id, String storeId, OrderStatus status,
                           String pickupCode, TimeRange placedTimeRange, String userId);
}
