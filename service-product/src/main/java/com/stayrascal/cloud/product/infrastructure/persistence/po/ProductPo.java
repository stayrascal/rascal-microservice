package com.stayrascal.cloud.product.infrastructure.persistence.po;

import com.stayrascal.cloud.common.contract.enumeration.CommonStatus;
import com.stayrascal.cloud.common.jpa.BasePo;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT")
public class ProductPo extends BasePo {

    @Column(name = "name", length = 64, nullable = false)
    private String name;

    @Column(name = "description", length = 64)
    private String description;

    @Column(name = "thumbnail", length = 32)
    private String thumbnail;

    @Column(name = "create_time_from", length = 22)
    private Long createTimeFrom;

    @Column(name = "create_time_to", length = 22)
    private Long createTimeTo;

    @Column(name = "filter_included", length = 16)
    private Boolean filterIncluded;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 16)
    private CommonStatus status;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private CategoryPo category;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private List<ProductItemPo> items;

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

    public Boolean getFilterIncluded() {
        return filterIncluded;
    }

    public void setFilterIncluded(Boolean filterIncluded) {
        this.filterIncluded = filterIncluded;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public CommonStatus getStatus() {
        return status;
    }

    public void setStatus(CommonStatus status) {
        this.status = status;
    }

    public CategoryPo getCategory() {
        return category;
    }

    public void setCategory(CategoryPo category) {
        this.category = category;
    }

    public List<ProductItemPo> getItems() {
        return items;
    }

    public void setItems(List<ProductItemPo> items) {
        this.items = items;
    }
}
