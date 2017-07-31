package com.stayrascal.cloud.product.domain.mapper;

import com.stayrascal.cloud.common.mapper.DefaultMapper;
import com.stayrascal.cloud.product.contract.dto.CategoryDto;
import com.stayrascal.cloud.product.domain.entity.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CategoryDtoMapper extends DefaultMapper {

    @Autowired
    private OptionDtoMapper optionDtoMapper;

    public CategoryDtoMapper() {
        register(CategoryDto.class, Category.class);
    }

    public Category categoryFromDto(CategoryDto categoryDto) {
        Category category = map(categoryDto, Category.class);
        if (categoryDto.getOptions() != null) {
            category.setProductOptions(categoryDto.getOptions().stream().map(optionDtoMapper::optionFromDto).collect(Collectors.toList()));
        }
        return category;
    }

    public CategoryDto categoryToDto(Category category) {
        CategoryDto categoryDto = map(category, CategoryDto.class);
        if (category.getProductOptions() != null) {
            categoryDto.setOptions(category.getProductOptions().stream().map(optionDtoMapper::optionToDto).collect(Collectors.toList()));
        }
        return categoryDto;
    }
}
