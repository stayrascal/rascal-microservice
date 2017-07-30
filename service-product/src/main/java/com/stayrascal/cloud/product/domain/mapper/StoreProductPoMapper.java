package com.stayrascal.cloud.product.domain.mapper;

import com.stayrascal.cloud.common.mapper.DefaultMapper;
import com.stayrascal.cloud.product.domain.entity.StoreProduct;
import com.stayrascal.cloud.product.domain.entity.StoreProductItem;
import com.stayrascal.cloud.product.infrastructure.persistence.po.StoreProductItemPo;
import com.stayrascal.cloud.product.infrastructure.persistence.po.StoreProductPo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class StoreProductPoMapper extends DefaultMapper {

    @Autowired
    private OptionPoMapper optionPoMapper;

    public StoreProductPoMapper() {
        register(StoreProductPo.class, StoreProduct.class);
        register(StoreProductItemPo.class, StoreProductItem.class);
    }

    public StoreProduct storeProductFromPo(StoreProductPo storeProductPo) {
        StoreProduct storeProduct = map(storeProductPo, StoreProduct.class);
        storeProduct.setItems(storeProductPo.getItems().stream().map(this::storeProductItemFromPo).collect(Collectors.toList()));
        return storeProduct;
    }

    public StoreProductItem storeProductItemFromPo(StoreProductItemPo storeProductItemPo) {
        StoreProductItem item = map(storeProductItemPo, StoreProductItem.class);
        item.setOptionPairs(storeProductItemPo.getOptionPairs().stream().map(optionPoMapper::optionPairFromPo).collect(Collectors.toList()));
        return item;
    }

    public StoreProductPo storeProductToPo(StoreProduct storeProduct) {
        StoreProductPo storeProductPo = map(storeProduct, StoreProductPo.class);
        storeProductPo.setItems(storeProduct.getItems().stream().map(this::storeProductItemToPo).collect(Collectors.toList()));
        return storeProductPo;
    }

    public StoreProductItemPo storeProductItemToPo(StoreProductItem storeProductItem) {
        StoreProductItemPo po = map(storeProductItem, StoreProductItemPo.class);
        po.setOptionPairs(storeProductItem.getOptionPairs().stream().map(optionPoMapper::optionPairToPo).collect(Collectors.toList()));
        return po;
    }
}
