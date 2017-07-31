package com.stayrascal.cloud.product.facade;

import com.stayrascal.cloud.common.constant.ErrorCode;
import com.stayrascal.cloud.common.contract.query.SortQuery;
import com.stayrascal.cloud.common.jersey.exception.NotFoundException;
import com.stayrascal.cloud.product.contract.command.CreateCategoryCommand;
import com.stayrascal.cloud.product.contract.command.CreateProductOptionCommand;
import com.stayrascal.cloud.product.contract.command.UpdateCategoryCommand;
import com.stayrascal.cloud.product.contract.dto.CategoryDto;
import com.stayrascal.cloud.product.domain.entity.Category;
import com.stayrascal.cloud.product.domain.entity.ProductOption;
import com.stayrascal.cloud.product.domain.factory.CategoryFactory;
import com.stayrascal.cloud.product.domain.mapper.CategoryDtoMapper;
import com.stayrascal.cloud.product.domain.mapper.OptionDtoMapper;
import com.stayrascal.cloud.product.infrastructure.persistence.JpaCategoryRepository;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Component
public class CategoryFacade {
    private final CategoryFactory categoryFactory;
    private final JpaCategoryRepository categoryRepository;
    private final CategoryDtoMapper mapper;
    private final OptionDtoMapper optionDtoMapper;

    @Autowired
    public CategoryFacade(JpaCategoryRepository categoryRepository, CategoryDtoMapper mapper,
                          CategoryFactory categoryFactory, OptionDtoMapper optionDtoMapper) {
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
        this.categoryFactory = categoryFactory;
        this.optionDtoMapper = optionDtoMapper;
    }

    public CategoryDto getCategoryById(String categoryId) {
        Category category = getCategory(categoryId);
        return mapper.categoryToDto(category);
    }

    private Category getCategory(String categoryId) {
        return categoryRepository.ofId(categoryId).orElseThrow(() ->
                new NotFoundException(ErrorCode.INTERNAL_ERROR, "Category of id {} not found", categoryId));
    }

    public List<CategoryDto> listCategories(SortQuery sortQuery, Map<String, String> queryMaps) {
        return categoryRepository.listCategories(sortQuery, queryMaps).stream().map(mapper::categoryToDto).collect(Collectors.toList());
    }

    public String createCategory(CreateCategoryCommand createCategoryCommand) {
        return categoryRepository.insert(categoryFactory.createCategory(createCategoryCommand));
    }

    public CategoryDto updateCategory(String categoryId, UpdateCategoryCommand command) {
        Category category = getCategory(categoryId);
        if (command.getIndex() != null) {
            category.setIndex(category.getIndex());
        }
        if (!Strings.isNullOrEmpty(command.getName())) {
            category.setName(category.getName());
        }
        if (category.getProductOptions() != null) {
            category.setProductOptions(command.getOptions().stream().map(optionDtoMapper::optionFromDto).collect(Collectors.toList()));
        }
        return mapper.categoryToDto(categoryRepository.update(category));
    }

    public String createProductOption(String categoryId, CreateProductOptionCommand command) {
        Category category = getCategory(categoryId);
        ProductOption productOption = categoryFactory.createProductOption(command);
        category.getProductOptions().add(productOption);
        categoryRepository.update(category);
        return productOption.getId();
    }
}
