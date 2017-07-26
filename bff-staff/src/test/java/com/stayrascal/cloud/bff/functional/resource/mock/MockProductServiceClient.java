package com.stayrascal.cloud.bff.functional.resource.mock;

import com.stayrascal.cloud.common.contract.enumeration.SortType;
import com.stayrascal.cloud.common.contract.result.CreatedResult;
import com.stayrascal.cloud.common.contract.result.PageResult;
import com.stayrascal.cloud.product.contract.client.ProductServiceClient;
import com.stayrascal.cloud.product.contract.command.CreateProductCommand;
import com.stayrascal.cloud.product.contract.command.UpdateProductCommand;
import com.stayrascal.cloud.product.contract.dto.ProductDto;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Component
public class MockProductServiceClient implements ProductServiceClient {


    @Override
    public CreatedResult createProduct(CreateProductCommand createProductCommand) {
        return null;
    }

    @Override
    public ProductDto getProduct(@PathVariable("id") String id) {
        return null;
    }

    @Override
    public PageResult listProducts(@RequestParam("sort_type") SortType sortType,
                                   @RequestParam("sort_by") String sortBy,
                                   @RequestParam("page_size") Integer pageSize,
                                   @RequestParam("page_index") Integer pageIndex,
                                   @RequestParam Map<String, String> queryMap) {
        return null;
    }

    @Override
    public PageResult listProductCatalogs(@RequestParam("sort_type") SortType sortType,
                                          @RequestParam("sort_by") String sortBy,
                                          @RequestParam("page_size") Integer pageSize,
                                          @RequestParam("page_index") Integer pageIndex,
                                          @RequestParam Map<String, String> queryMap) {
        return null;
    }

    @Override
    public void updateProduct(@PathVariable("id") String id, UpdateProductCommand updateProductCommand) {
    }

    @Override
    public void deleteProduct(@PathVariable("id") String id) {

    }
}
