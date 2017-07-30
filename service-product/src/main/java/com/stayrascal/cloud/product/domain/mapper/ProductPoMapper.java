package com.stayrascal.cloud.product.domain.mapper;

import com.stayrascal.cloud.common.mapper.DefaultMapper;
import com.stayrascal.cloud.product.domain.entity.Product;
import com.stayrascal.cloud.product.domain.entity.ProductItem;
import com.stayrascal.cloud.product.infrastructure.persistence.po.ProductItemPo;
import com.stayrascal.cloud.product.infrastructure.persistence.po.ProductPo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ProductPoMapper extends DefaultMapper {

    @Autowired
    private OptionPoMapper optionPoMapper;
    @Autowired
    private CategoryPoMapper categoryPoMapper;

    public ProductPoMapper() {
        register(ProductPo.class, Product.class);
        register(ProductItemPo.class, ProductItem.class);
    }

    public Product productFromPo(ProductPo productPo) {
        Product product = map(productPo, Product.class);
        if (productPo.getItems() != null) {
            product.setItems(productPo.getItems().stream().map(this::productItemFromPo).collect(Collectors.toList()));
        }
        if (productPo.getCategory() != null) {
            product.setCategory(categoryPoMapper.categoryFromPo(productPo.getCategory()));
        }
        return product;
    }


    private ProductItem productItemFromPo(ProductItemPo productItemPo) {
        ProductItem productItem = map(productItemPo, ProductItem.class);
        if (productItemPo.getOptionPairs() != null) {
            productItem.setOptionPairDtos(productItemPo.getOptionPairs().stream().map(optionPoMapper::optionPairFromPo).collect(Collectors.toList()));
        }
        return productItem;
    }

    public ProductPo productToPo(Product product) {
        ProductPo productPo = map(product, ProductPo.class);
        if (product.getItems() != null) {
            productPo.setItems(product.getItems().stream().map(this::productItemToPo).collect(Collectors.toList()));
        }
        if (product.getCategory() != null) {
            productPo.setCategory(categoryPoMapper.categoryToPo(product.getCategory()));
        }
        return productPo;
    }

    private ProductItemPo productItemToPo(ProductItem productItem) {
        ProductItemPo productItemPo = map(productItem, ProductItemPo.class);
        if (productItem.getOptionPairDtos() != null) {
            productItemPo.setOptionPairs(productItem.getOptionPairDtos().stream().map(optionPoMapper::optionPairToPo).collect(Collectors.toList()));
        }
        return productItemPo;
    }

}

