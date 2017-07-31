package com.stayrascal.cloud.product.domain.mapper;

import com.stayrascal.cloud.common.mapper.DefaultMapper;
import com.stayrascal.cloud.product.contract.dto.StoreProductDto;
import com.stayrascal.cloud.product.contract.dto.StoreProductItemDto;
import com.stayrascal.cloud.product.domain.entity.StoreProduct;
import com.stayrascal.cloud.product.domain.entity.StoreProductItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class StoreProductDtoMapper extends DefaultMapper {

    @Autowired
    private OptionDtoMapper optionDtoMapper;

    public StoreProductDtoMapper() {
        register(StoreProductDto.class, StoreProduct.class);
        register(StoreProductItemDto.class, StoreProductItem.class);
    }

    public StoreProductDto storeProductToDto(StoreProduct storeProduct) {
        StoreProductDto storeProductDto = map(storeProduct, StoreProductDto.class);
        if (storeProduct.getItems() != null) {
            storeProductDto.setItems(storeProduct.getItems().stream().map(this::storeProductItemToDto).collect(Collectors.toList()));
        }
        return storeProductDto;
    }

    public StoreProductItemDto storeProductItemToDto(StoreProductItem storeProductItem) {
        StoreProductItemDto dto = map(storeProductItem, StoreProductItemDto.class);
        if (storeProductItem.getOptionPairs() != null) {
            dto.setOptionPairDtos(storeProductItem.getOptionPairs().stream().map(optionDtoMapper::optionPairToDto).collect(Collectors.toList()));
        }
        return dto;
    }
}
