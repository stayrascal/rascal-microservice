package com.stayrascal.cloud.product.domain.factory;

import com.stayrascal.cloud.common.jpa.UniqueKeyGenerator;
import com.stayrascal.cloud.common.mapper.DefaultMapper;
import com.stayrascal.cloud.product.contract.command.CreateStoreProductCommand;
import com.stayrascal.cloud.product.contract.enumeration.StoreProductStatus;
import com.stayrascal.cloud.product.domain.entity.StoreProduct;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StoreProductFactory {
    private final UniqueKeyGenerator uniqueKeyGenerator;
    private final DefaultMapper mapper;

    @Autowired
    public StoreProductFactory(UniqueKeyGenerator generator) {
        this.uniqueKeyGenerator = generator;
        this.mapper = DefaultMapper.builder()
                .register(CreateStoreProductCommand.class, StoreProduct.class)
                .build();
    }

    public StoreProduct createStoreProduct(CreateStoreProductCommand command) {
        StoreProduct storeProduct = mapper.map(command, StoreProduct.class);
        storeProduct.setId(uniqueKeyGenerator.generateKey());
        storeProduct.setTimeCreated(DateTime.now().toDate());
        storeProduct.setStatus(StoreProductStatus.ACTIVE);
        return storeProduct;
    }
}
