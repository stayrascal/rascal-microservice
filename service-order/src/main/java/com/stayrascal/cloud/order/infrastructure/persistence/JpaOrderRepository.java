package com.stayrascal.cloud.order.infrastructure.persistence;

import static com.exmertec.yaz.BaseDao.field;

import com.stayrascal.cloud.common.util.TimeRange;
import com.stayrascal.cloud.order.contract.enumeration.OrderStatus;
import com.stayrascal.cloud.order.domain.entity.Order;
import com.stayrascal.cloud.order.domain.mapper.OrderPoMapper;
import com.stayrascal.cloud.order.domain.repository.OrderRepository;
import com.stayrascal.cloud.order.infrastructure.persistence.po.OrderPo;
import com.stayrascal.clould.common.contract.enumeration.SortType;
import com.stayrascal.clould.common.contract.query.SortQuery;

import com.exmertec.yaz.BaseDao;
import com.exmertec.yaz.core.OrderType;
import com.exmertec.yaz.core.Query;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;

@Component
public class JpaOrderRepository implements OrderRepository {
    private final BaseDao<OrderPo> orderDao;
    private final OrderPoMapper orderPoMapper;

    @Autowired
    public JpaOrderRepository(EntityManager entityManager, OrderPoMapper orderPoMapper) {
        orderDao = new BaseDao<>(entityManager, OrderPo.class);
        this.orderPoMapper = orderPoMapper;
    }

    @Override
    public Optional<Order> ofId(String id) {
        final OrderPo orderPo = orderDao.idEquals(id).querySingle();
        if (orderPo == null) {
            return Optional.empty();
        }
        final Order order = orderPoMapper.orderFromPo(orderPo);
        return Optional.of(order);
    }

    @Override
    public String insert(Order order) {
        OrderPo orderPo = orderPoMapper.orderToPo(order);
        orderDao.save(orderPo);
        return orderPo.getId();
    }

    @Override
    public Order update(Order order) {
        orderDao.update(orderPoMapper.orderToPo(order));
        return order;
    }

    @Override
    public long countOrders(String id, String storeId, OrderStatus status, String pickupCode, TimeRange placedTimeRange, String userId) {
        return orderDao.where(generateListQueries(id, storeId, status, pickupCode, placedTimeRange, userId)).count();
    }

    @Override
    public List<Order> listOrders(SortQuery sortQuery, String id, String storeId, OrderStatus status,
                                  String pickupCode, TimeRange placedTimeRange, String userId) {
        Query[] queries = generateListQueries(id, storeId, status, pickupCode, placedTimeRange, userId);
        OrderType orderType = (sortQuery.getSortType() == SortType.ASC ? OrderType.ASCENDING : OrderType.DESCENDING);
        return orderDao.where(queries).orderBy(orderType, sortQuery.getSortBy())
                .queryPage(sortQuery.getPageSize(), sortQuery.getPageIndex())
                .stream()
                .map(orderPoMapper::orderFromPo)
                .collect(Collectors.toList());
    }

    private Query[] generateListQueries(String id, String storeId, OrderStatus status, String pickupCode, TimeRange placedTimeRange, String userId) {
        List<Query> queries = Lists.newArrayList();
        if (!Strings.isNullOrEmpty(id)) {
            queries.add(field("id").like(id));
        }

        if (!Strings.isNullOrEmpty(storeId)) {
            queries.add(field("storeId").eq(storeId));
        }

        if (status != null) {
            queries.add(field("status").eq(status));
        }

        if (!Strings.isNullOrEmpty(pickupCode)) {
            queries.add(field("pickupCode").like(pickupCode));
        }

        if (placedTimeRange.getStartTime() != null) {
            queries.add(field("placedTime").gteComparable(placedTimeRange.getStartTime()));
        }

        if (placedTimeRange.getEndTime() != null) {
            queries.add(field("placedTime").lteComparable(placedTimeRange.getEndTime()));
        }

        if (!Strings.isNullOrEmpty(userId)) {
            queries.add(field("userId").eq(userId));
        }

        return queries.toArray(new Query[0]);
    }
}
