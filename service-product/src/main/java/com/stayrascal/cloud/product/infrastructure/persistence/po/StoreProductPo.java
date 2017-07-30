package com.stayrascal.cloud.product.infrastructure.persistence.po;

import com.stayrascal.cloud.common.jpa.BasePo;
import com.stayrascal.cloud.product.contract.enumeration.StoreProductStatus;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "STORE_PRODUCT")
public class StoreProductPo extends BasePo {
    @Column(name = "product_id", length = 64, nullable = false, updatable = false)
    private String productId;

    @Column(name = "category_id", length = 64, nullable = false, updatable = false)
    private String categoryId;

    @Column(name = "store_id", length = 64, nullable = false)
    private String storeId;

    @Column(name = "name", length = 32, nullable = false)
    private String name;

    @Column(name = "description", length = 64)
    private String description;

    @Column(name = "thumbnail", length = 32)
    private String thumbnail;

    @Column(name = "create_time_from", length = 32)
    private Long createTimeFrom;

    @Column(name = "create_time_to", length = 32)
    private Long createTimeTo;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private List<StoreProductItemPo> items;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 32)
    private StoreProductStatus status;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public List<StoreProductItemPo> getItems() {
        return items;
    }

    public void setItems(List<StoreProductItemPo> items) {
        this.items = items;
    }

    public StoreProductStatus getStatus() {
        return status;
    }

    public void setStatus(StoreProductStatus status) {
        this.status = status;
    }

    public Long getCreateTimeFrom() {
        return createTimeFrom;
    }

    public void setCreateTimeFrom(Long createTimeFrom) {
        this.createTimeFrom = createTimeFrom;
    }

    public Long getCreateTimeTo() {
        return createTimeTo;
    }

    public void setCreateTimeTo(Long createTimeTo) {
        this.createTimeTo = createTimeTo;
    }
}
