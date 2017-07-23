package com.stayrascal.cloud.order.domain.entity;

import com.stayrascal.cloud.common.constant.ErrorCode;
import com.stayrascal.cloud.common.jersey.exception.BadRequestException;
import com.stayrascal.cloud.common.jersey.exception.InternalErrorException;
import com.stayrascal.cloud.order.constant.DefaultValues;
import com.stayrascal.cloud.order.contract.enumeration.DeliveryMethod;
import com.stayrascal.cloud.order.contract.enumeration.OrderStatus;
import com.stayrascal.cloud.order.service.PickupCodeGeneratorService;

import com.google.common.base.Strings;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Order {

    public static final Logger LOGGER = LoggerFactory.getLogger(Order.class);

    private String id;

    private String userId;

    private String storeId;

    private String note;

    private String pickupCode;

    private String transactionId;

    private DeliveryMethod deliveryMethod;

    private OrderStatus status;

    private List<OrderItem> items;

    private Date timeCreated;

    private Date placedTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getPickupCode() {
        return pickupCode;
    }

    public void setPickupCode(String pickupCode) {
        this.pickupCode = pickupCode;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public DeliveryMethod getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(DeliveryMethod deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public Date getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }

    public Date getPlacedTime() {
        return placedTime;
    }

    public void setPlacedTime(Date placedTime) {
        this.placedTime = placedTime;
    }

    public void updateNote(String newNote) {
        if (newNote != null && newNote.length() > DefaultValues.NOTE_LENGTH_LIMIT) {
            throw new BadRequestException(ErrorCode.INTERNAL_ERROR,
                    "Note length is limited to {}, but length of note to add is {}",
                    DefaultValues.NOTE_LENGTH_LIMIT, newNote.length());
        }
        if (getStatus() != OrderStatus.OPEN) {
            throw new BadRequestException(ErrorCode.INTERNAL_ERROR, "Can only set note on OPEN orders");
        }
        setNote(newNote);
    }

    public void transactionFinished(String transactionId, PickupCodeGeneratorService pickupCodeGeneratorService) {
        if (Strings.isNullOrEmpty(transactionId)) {
            throw new BadRequestException(ErrorCode.INTERNAL_ERROR,
                    "Transaction ID is empty when finishing on order {}", getId());
        }

        if (getStatus() != OrderStatus.OPEN) {
            throw new BadRequestException(ErrorCode.INTERNAL_ERROR,
                    "Transaction {} finished on non-open order {}", transactionId, getId());
        }

        if (getTransactionId() != null) {
            throw new BadRequestException(ErrorCode.INTERNAL_ERROR, "order {} transaction already finished", getId());
        }

        // TODO: lock the stock for the purchased store items
        placeOrder();
        setTransactionId(transactionId);

        if (getDeliveryMethod() == DeliveryMethod.STORE_PICKUP) {
            setPickupCode(pickupCodeGeneratorService.generateFetchCode(getStoreId()));
        }
    }

    private void placeOrder() {
        if (getStatus() != OrderStatus.OPEN) {
            throw new BadRequestException(ErrorCode.INTERNAL_ERROR,
                    "Can only place on OPEN orders, but order {} is on status {}", getId(), getStatus());
        }

        setStatus(OrderStatus.PROCESSING);
        setPlacedTime(DateTime.now().toDate());
    }

    public void updateStatus(OrderStatus newStatus) {
        switch (newStatus) {
            case CLOSED: {
                close();
                break;
            }
            case CANCELLED: {
                cancel();
                break;
            }
            case FULFILLED: {
                fullfill();
                break;
            }
            case PROCESSING:
            case OPEN:
            default:
                LOGGER.error("New order status {} not allowed", newStatus);
                break;
        }
    }

    private void fullfill() {
        if (getStatus() != OrderStatus.PROCESSING) {
            throw new BadRequestException(ErrorCode.INTERNAL_ERROR,
                    "Can only fullfill processing orders, but order {} is in status {}", getId(), getStatus());
        }

        setStatus(OrderStatus.FULFILLED);
    }

    private void cancel() {
        if (getStatus() != OrderStatus.OPEN) {
            throw new BadRequestException(ErrorCode.INTERNAL_ERROR, "Cannot cancel non-open order {}", getId());
        }

        if (getTransactionId() == null) {
            throw new BadRequestException(ErrorCode.INTERNAL_ERROR,
                    "Cannot cancel order {} without transaction finished", getId());
        }

        setStatus(OrderStatus.CANCELLED);
    }

    private void close() {
        if (getStatus() != OrderStatus.OPEN) {
            throw new BadRequestException(ErrorCode.INTERNAL_ERROR, "Cannot close non-open order {}", getId());
        }

        if (getTransactionId() != null) {
            throw new BadRequestException(ErrorCode.INTERNAL_ERROR,
                    "Cannot close order {}, which already has transaction finished", getId());
        }

        setStatus(OrderStatus.CLOSED);
    }

    public BigDecimal getTotalAmount() {
        return items.stream().map(item -> item.getPrice().multiply(new BigDecimal(item.getQuantity())))
                .reduce(BigDecimal::add).orElseThrow(() -> new InternalErrorException(ErrorCode.INTERNAL_ERROR,
                        "Failed to calculate order total amount on order {}, no order item?", getId()));
    }
}
