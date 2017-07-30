package com.stayrascal.cloud.product.domain.factory;

import com.stayrascal.cloud.common.contract.enumeration.CommonStatus;
import com.stayrascal.cloud.common.jpa.UniqueKeyGenerator;
import com.stayrascal.cloud.common.mapper.DefaultMapper;
import com.stayrascal.cloud.product.contract.command.CreateProductCommand;
import com.stayrascal.cloud.product.contract.command.CreateProductItemCommand;
import com.stayrascal.cloud.product.contract.dto.CategoryDto;
import com.stayrascal.cloud.product.contract.dto.ProductDto;
import com.stayrascal.cloud.product.contract.dto.ProductItemDto;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ProductFactory {
    private final UniqueKeyGenerator uniqueKeyGenerator;
    private final DefaultMapper mapper;

    @Autowired
    public ProductFactory(UniqueKeyGenerator uniqueKeyGenerator) {
        this.uniqueKeyGenerator = uniqueKeyGenerator;
        this.mapper = DefaultMapper.builder()
                .register(CreateProductCommand.class, ProductDto.class)
                .register(CreateProductItemCommand.class, ProductItemDto.class)
                .build();
    }

    public ProductDto createProduct(CreateProductCommand command) {
        ProductDto productDto = mapper.map(command, ProductDto.class);
        if (command.getCreateProductItemCommands() != null){
            productDto.setItems(command.getCreateProductItemCommands().stream().map(this::createProductItem).collect(Collectors.toList()));
        }
        if (command.getCategoryId() != null){
            productDto.setCategory(this.createCategory(command.getCategoryId()));
        }
        productDto.setId(uniqueKeyGenerator.generateKey());
        productDto.setStatus(CommonStatus.ENABLED);
        productDto.setTimeCreated(DateTime.now().toDate());
        return productDto;
    }

    private CategoryDto createCategory(String categoryId) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(categoryId);
        return categoryDto;
    }

    private ProductItemDto createProductItem(CreateProductItemCommand createProductItemCommand) {
        ProductItemDto productItemDto = mapper.map(createProductItemCommand, ProductItemDto.class);
        productItemDto.setId(uniqueKeyGenerator.generateKey());
        productItemDto.setOptionPairDtos(createProductItemCommand.getOptionPairDtos());
        return productItemDto;
    }
}
