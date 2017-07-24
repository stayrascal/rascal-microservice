package com.stayrascal.cloud.product.infrastructure.factory;

import com.stayrascal.cloud.common.ddd.EventSender;
import com.stayrascal.cloud.common.jpa.UniqueKeyGenerator;
import com.stayrascal.cloud.product.contract.dto.ProductDto;
import com.stayrascal.cloud.product.domain.entity.Product;
import com.stayrascal.cloud.product.infrastructure.persistence.ProductPo;
import com.stayrascal.cloud.product.infrastructure.persistence.ProductPoMapper;
import com.stayrascal.cloud.product.infrastructure.persistence.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductFactory {
    private final UniqueKeyGenerator uniqueKeyGenerator;
    private final EventSender eventSender;
    private final ProductPoMapper mapper;
    private final ProductRepository productRepository;

    @Autowired
    public ProductFactory(UniqueKeyGenerator uniqueKeyGenerator,
                          ProductRepository productRepository,
                          EventSender eventSender) {
        this.uniqueKeyGenerator = uniqueKeyGenerator;
        this.productRepository = productRepository;
        this.eventSender = eventSender;
        this.mapper = new ProductPoMapper();
    }

    public Product createWithDto(ProductDto productDto) {
        ProductPo product = mapper.map(productDto, ProductPo.class);
        product.setId(uniqueKeyGenerator.generateKey());
        productRepository.save(product);

        return toProduct(product).get();
    }

    public Optional<Product> createWithId(String id) {
        return toProduct(productRepository.ofId(id));
    }

    private Optional<Product> toProduct(ProductPo productPo) {
        Product product = mapper.map(productPo, Product.class);
        return Optional.of(product);
    }
}
