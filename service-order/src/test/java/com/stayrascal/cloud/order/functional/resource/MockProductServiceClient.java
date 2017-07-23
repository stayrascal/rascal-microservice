package com.stayrascal.cloud.order.functional.resource;

import com.stayrascal.cloud.product.contract.client.ProductServiceClient;
import com.stayrascal.cloud.product.contract.command.CreateProductCommand;
import com.stayrascal.cloud.product.contract.command.UpdateProductInfoCommand;
import com.stayrascal.cloud.product.contract.dto.ProductDto;
import com.stayrascal.clould.common.contract.enumeration.SortType;
import com.stayrascal.clould.common.contract.result.CreatedResult;
import com.stayrascal.clould.common.contract.result.PageResult;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class MockProductServiceClient implements ProductServiceClient {

    @Override
    public CreatedResult create(CreateProductCommand createProductCommand) {
        return null;
    }

    @Override
    public ProductDto get(@PathVariable("id") String id) {
        return null;
    }

    @Override
    public PageResult list(@RequestParam("sort_type") String sortType,
                           @RequestParam("sort_by") String sortBy,
                           @RequestParam("page_size") Integer pageSize,
                           @RequestParam("page_index") Integer pageIndex) {
        return null;
    }

    @Override
    public PageResult list(@RequestParam("store_id") String sid, @RequestParam("category_id") String cid,
                           @RequestParam("create_time_from") Long from, @RequestParam("create_time_to") Long to,
                           @RequestParam("name") String name, @RequestParam("filter_included") Boolean filter,
                           @RequestParam("sort_type") SortType sortType, @RequestParam("sort_by") String sortBy,
                           @RequestParam("page_size") Integer ps, @RequestParam("page_index") Integer pi) {
        return null;
    }

    @Override
    public void update(@PathVariable("id") String id, UpdateProductInfoCommand updateProductInfoCommand) {

    }
}
