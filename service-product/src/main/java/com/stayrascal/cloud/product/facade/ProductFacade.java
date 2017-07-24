package com.stayrascal.cloud.product.facade;

import com.stayrascal.cloud.common.ddd.EventSender;
import com.stayrascal.cloud.product.contract.command.CreateProductCommand;
import com.stayrascal.cloud.product.contract.command.UpdateProductCommand;
import com.stayrascal.cloud.product.contract.dto.ProductDto;
import com.stayrascal.cloud.product.infrastructure.factory.ProductFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class ProductFacade {
    private final ProductFactory productFactory;
    private final EventSender eventSender;

    @Autowired
    public ProductFacade(ProductFactory productFactory,
                         EventSender eventSender) {
        this.productFactory = productFactory;
        this.eventSender = eventSender;
    }

    public ProductDto getProductById(String id) {
        return null;
    }

    public String createProduct(CreateProductCommand createProductCommand) {
        return null;
    }

    public void updateProductInfo(String productId, UpdateProductCommand command) {
    }
}
