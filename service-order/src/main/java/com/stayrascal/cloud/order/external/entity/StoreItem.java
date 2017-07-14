package com.stayrascal.cloud.order.external.entity;

import com.stayrascal.cloud.product.contract.dto.StoreItemDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class StoreItem {
    private String id;

    private Integer quantity;

    private BigDecimal price;

    private List<OptionPair> optionPairs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<OptionPair> getOptionPairs() {
        return optionPairs;
    }

    public void setOptionPairs(List<OptionPair> optionPairs) {
        this.optionPairs = optionPairs;
    }

    public static StoreItem mapFrom(StoreItemDto storeItem) {
        StoreItem item = new StoreItem();
        item.setId(storeItem.getId());
        item.setPrice(storeItem.getPrice());
        item.setQuantity(storeItem.getQuantity());
        List<OptionPair> optionPairs = storeItem.getOptionPairs().stream()
                .map(OptionPair::mapFrom).collect(Collectors.toList());
        item.setOptionPairs(optionPairs);
        return item;
    }
}
