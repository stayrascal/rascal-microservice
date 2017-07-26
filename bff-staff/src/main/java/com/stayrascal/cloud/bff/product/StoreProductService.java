package com.stayrascal.cloud.bff.product;

import com.stayrascal.cloud.bff.common.BatchResponseElement;
import com.stayrascal.cloud.bff.product.request.BatchCreateStoreProductCommand;
import com.stayrascal.cloud.bff.product.request.BatchUpdateStoreProductCommand;
import com.stayrascal.cloud.bff.product.response.StoreProductWithCategory;
import com.stayrascal.cloud.common.contract.enumeration.SortType;
import com.stayrascal.cloud.common.contract.result.CreatedResult;
import com.stayrascal.cloud.common.contract.result.PageResult;
import com.stayrascal.cloud.common.mapper.DefaultMapper;
import com.stayrascal.cloud.product.contract.client.CategoryServiceClient;
import com.stayrascal.cloud.product.contract.client.StoreProductServiceClient;
import com.stayrascal.cloud.product.contract.dto.StoreProductDto;
import com.stayrascal.cloud.product.contract.enumeration.StoreProductStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StoreProductService {
    private final StoreProductServiceClient storeProductServiceClient;
    private final CategoryServiceClient categoryServiceClient;
    private final DefaultMapper mapper;

    @Autowired
    public StoreProductService(StoreProductServiceClient storeProductServiceClient,
                               CategoryServiceClient categoryServiceClient) {
        this.storeProductServiceClient = storeProductServiceClient;
        this.categoryServiceClient = categoryServiceClient;
        this.mapper = DefaultMapper.builder().build();
    }

    public List<BatchResponseElement> batchCreateStoreProduct(BatchCreateStoreProductCommand batch) {
        return batch.getCommands()
                .stream().map(cmd -> {
                    try {
                        CreatedResult idResult = storeProductServiceClient.createStoreProduct(cmd);
                        return new BatchResponseElement(false, cmd, idResult);
                    } catch (Exception e) {
                        return new BatchResponseElement(true, cmd, e.getMessage());
                    }
                }).collect(Collectors.toList());
    }

    public List<BatchResponseElement> batchUpdateStoreProduct(BatchUpdateStoreProductCommand batch) {
        return batch.getCommands()
                .stream().map(cmd -> {
                    try {
                        storeProductServiceClient.updateStoreProduct(cmd.getId(), cmd);
                        return new BatchResponseElement(false, cmd, null);
                    } catch (Exception e) {
                        return new BatchResponseElement(true, cmd, e.getMessage());
                    }
                }).collect(Collectors.toList());
    }

    public PageResult listProductByStore(String categoryId, String storeId, Long createTimeFrom,
                                         Long createTimeTo, String name, StoreProductStatus status,
                                         SortType sortType, String sortBy,
                                         Integer pageSize, Integer pageIndex) {
        final PageResult pageResult = storeProductServiceClient.listStoreProducts(
                sortType, sortBy, pageSize, pageIndex, categoryId, storeId,
                createTimeFrom, createTimeTo, name, status);
        pageResult.setItems((List) pageResult.getItems()
                .stream()
                .map(data -> toStoreProductWithCategory((StoreProductDto) data))
                .collect(Collectors.toList()));
        return pageResult;
    }

    private StoreProductWithCategory toStoreProductWithCategory(StoreProductDto storeProductDto) {
        final StoreProductWithCategory result = mapper.map(storeProductDto, StoreProductWithCategory.class);
        result.setCategoryName(categoryServiceClient.getCategory(storeProductDto.getCategoryId()).getName());
        return result;
    }
}
