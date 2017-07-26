package com.stayrascal.cloud.bff.functional.resource.mock;

import com.stayrascal.cloud.common.contract.enumeration.SortType;
import com.stayrascal.cloud.common.contract.result.CreatedResult;
import com.stayrascal.cloud.common.contract.result.ListResult;
import com.stayrascal.cloud.common.contract.result.PageResult;
import com.stayrascal.cloud.product.contract.client.CategoryServiceClient;
import com.stayrascal.cloud.product.contract.command.CreateCategoryCommand;
import com.stayrascal.cloud.product.contract.command.CreateProductOptionCommand;
import com.stayrascal.cloud.product.contract.command.UpdateCategoryCommand;
import com.stayrascal.cloud.product.contract.dto.CategoryDto;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MockCategoryClient implements CategoryServiceClient {
    @Override
    public CreatedResult createCategory(CreateCategoryCommand var1) {
        return null;
    }

    @Override
    public CategoryDto getCategory(String var1) {
        return null;
    }

    @Override
    public PageResult listCategories(SortType var1, String var2, Integer var3, Integer var4, Map<String, String> var5) {
        return null;
    }

    @Override
    public void updateCategory(String var1, UpdateCategoryCommand var2) {

    }

    @Override
    public ListResult listStoreCategories(String var1) {
        return null;
    }

    @Override
    public CreatedResult createOptions(String var1, CreateProductOptionCommand var2) {
        return null;
    }
}
