package com.stayrascal.cloud.product.domain.factory;

import com.stayrascal.cloud.common.jpa.UniqueKeyGenerator;
import com.stayrascal.cloud.common.mapper.DefaultMapper;
import com.stayrascal.cloud.product.contract.command.CreateCategoryCommand;
import com.stayrascal.cloud.product.contract.command.CreateProductOptionCommand;
import com.stayrascal.cloud.product.domain.entity.Category;
import com.stayrascal.cloud.product.domain.entity.OptionValue;
import com.stayrascal.cloud.product.domain.entity.ProductOption;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CategoryFactory {
    private final UniqueKeyGenerator generator;
    private final DefaultMapper mapper;

    @Autowired
    public CategoryFactory(UniqueKeyGenerator generator) {
        this.generator = generator;
        this.mapper = DefaultMapper.builder()
                .register(CreateCategoryCommand.class, Category.class)
                .register(CreateProductOptionCommand.class, ProductOption.class)
                .build();
    }

    public Category createCategory(CreateCategoryCommand createCategoryCommand) {
        Category category = mapper.map(createCategoryCommand, Category.class);
        category.setId(generator.generateKey());
        category.setTimeCreated(DateTime.now().toDate());
        category.setProductOptions(createCategoryCommand.getOptionCommands().stream().map(this::createProductOption).collect(Collectors.toList()));
        return category;
    }

    public ProductOption createProductOption(CreateProductOptionCommand createProductOptionCommand) {
        ProductOption productOption = mapper.map(createProductOptionCommand, ProductOption.class);
        productOption.setId(generator.generateKey());
        productOption.setTimeCreated(DateTime.now().toDate());
        productOption.setValues(createProductOptionCommand.getValues().stream().map(this::createProductOptionValue).collect(Collectors.toList()));
        return productOption;
    }

    private OptionValue createProductOptionValue(String value) {
        OptionValue optionValue = new OptionValue();
        optionValue.setId(generator.generateKey());
        optionValue.setValue(value);
        optionValue.setTimeCreated(DateTime.now().toDate());
        return optionValue;
    }
}
