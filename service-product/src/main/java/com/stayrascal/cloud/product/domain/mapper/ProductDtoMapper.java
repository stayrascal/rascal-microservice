package com.stayrascal.cloud.product.domain.mapper;

import com.stayrascal.cloud.common.mapper.DefaultMapper;
import com.stayrascal.cloud.product.contract.dto.ProductDto;
import com.stayrascal.cloud.product.contract.dto.ProductItemDto;
import com.stayrascal.cloud.product.domain.entity.Product;
import com.stayrascal.cloud.product.domain.entity.ProductItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ProductDtoMapper extends DefaultMapper {

    @Autowired
    private OptionDtoMapper optionDtoMapper;

    @Autowired
    private CategoryDtoMapper categoryDtoMapper;


    public ProductDtoMapper() {
        register(ProductDto.class, Product.class);
        register(ProductItemDto.class, ProductItem.class);
    }

    public Product productFromDto(ProductDto productDto) {
        Product product = map(productDto, Product.class);
        if (product.getItems() != null) {
            product.setItems(productDto.getItems().stream().map(this::productItemFromDto).collect(Collectors.toList()));
        }
        if (product.getCategory() != null) {
            product.setCategory(categoryDtoMapper.categoryFromDto(productDto.getCategory()));
        }
        return product;
    }


    public ProductItem productItemFromDto(ProductItemDto itemDto) {
        ProductItem productItem = map(itemDto, ProductItem.class);
        if (itemDto.getOptionPairDtos() != null) {
            productItem.setOptionPairDtos(itemDto.getOptionPairDtos().stream().map(optionDtoMapper::optionPairFromDto).collect(Collectors.toList()));
        }
        return productItem;
    }

    public ProductDto productToDto(Product product) {
        ProductDto productDto = map(product, ProductDto.class);
        if (product.getItems() != null) {
            productDto.setItems(product.getItems().stream().map(this::productItemToDto).collect(Collectors.toList()));
        }
        if (product.getCategory() != null) {
            productDto.setCategory(categoryDtoMapper.categoryToDto(product.getCategory()));
        }
        return productDto;
    }


    public ProductItemDto productItemToDto(ProductItem item) {
        ProductItemDto productItemDto = map(item, ProductItemDto.class);
        if (item.getOptionPairDtos() != null) {
            productItemDto.setOptionPairDtos(item.getOptionPairDtos().stream().map(optionDtoMapper::optionPairToDto).collect(Collectors.toList()));
        }
        return productItemDto;
    }
}
