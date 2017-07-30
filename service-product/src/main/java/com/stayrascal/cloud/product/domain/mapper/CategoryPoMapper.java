package com.stayrascal.cloud.product.domain.mapper;

import com.stayrascal.cloud.common.mapper.DefaultMapper;
import com.stayrascal.cloud.product.domain.entity.Category;
import com.stayrascal.cloud.product.infrastructure.persistence.po.CategoryPo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CategoryPoMapper extends DefaultMapper {

    @Autowired
    private OptionPoMapper optionPoMapper;

    public CategoryPoMapper() {
        register(CategoryPo.class, Category.class);
    }

    public Category categoryFromPo(CategoryPo categoryPo) {
        Category category = map(categoryPo, Category.class);
        if (categoryPo.getOptions() != null){
            category.setProductOptions(categoryPo.getOptions().stream().map(optionPoMapper::productOptionFromPo).collect(Collectors.toList()));
        }
        return category;
    }


    public CategoryPo categoryToPo(Category category) {
        CategoryPo categoryPo = map(category, CategoryPo.class);
        if (category.getProductOptions() != null){
            categoryPo.setOptions(category.getProductOptions().stream().map(optionPoMapper::productOptionToPo).collect(Collectors.toList()));
        }
        return categoryPo;
    }

}
