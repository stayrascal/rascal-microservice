package com.stayrascal.cloud.order.infrastructure.persistence.po;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ORDER_ITEM")
public class OrderItemPo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "order_id", length = 64, nullable = false, updatable = false)
    private String orderId;

    @Column(name = "store_product_id", length = 64, nullable = false, updatable = false)
    private String storeProductId;

    @Column(name = "store_item_id", length = 64, nullable = false, updatable = false)
    private String storeItemId;

    @Column(name = "store_product_name", length = 64, nullable = false, updatable = false)
    private String storeProductName;

    @Column(name = "store_item_name", length = 128, nullable = false, updatable = false)
    private String storeItemName;

    @Column(name = "quantity", nullable = false, updatable = false)
    private Integer quantity;

    @Column(name = "price", nullable = false,
            updatable = false, precision = 2,
            scale = 15, columnDefinition = "decimal")
    private BigDecimal price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

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
