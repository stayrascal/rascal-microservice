package com.stayrascal.cloud.bff.product;

import com.stayrascal.cloud.bff.product.response.ProductResponse;
import com.stayrascal.cloud.common.contract.QueryMap;
import com.stayrascal.cloud.common.contract.result.PageResult;
import com.stayrascal.cloud.common.mapper.DefaultMapper;
import com.stayrascal.cloud.product.contract.client.ProductServiceClient;
import com.stayrascal.cloud.product.contract.client.StoreProductServiceClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ProductService {
    private final ProductServiceClient productServiceClient;
    private final StoreProductServiceClient storeProductServiceClient;
    private final DefaultMapper mapper;

    @Autowired
    public ProductService(ProductServiceClient productServiceClient, StoreProductServiceClient storeProductServiceClient) {
        this.productServiceClient = productServiceClient;
        this.storeProductServiceClient = storeProductServiceClient;
        this.mapper = DefaultMapper.builder().build();
    }

    public ProductResponse getProduct(String id) {
        final ProductResponse response = mapper.map(productServiceClient.getProduct(id), ProductResponse.class);
        Map<String, String> params = QueryMap.builder().put("product_id", id).build();
        final PageResult pageResult = storeProductServiceClient.listStoreProducts(1, 0, params);
        response.setReferenceByStoreProduct(pageResult.getTotalCount() > 0L);
        return response;
    }
}
