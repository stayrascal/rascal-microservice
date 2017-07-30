package com.stayrascal.cloud.product.facade;

import com.stayrascal.cloud.common.constant.ErrorCode;
import com.stayrascal.cloud.common.contract.query.SortQuery;
import com.stayrascal.cloud.common.jersey.exception.NotFoundException;
import com.stayrascal.cloud.product.contract.command.CreateProductCommand;
import com.stayrascal.cloud.product.contract.command.UpdateProductCommand;
import com.stayrascal.cloud.product.contract.dto.ProductDto;
import com.stayrascal.cloud.product.domain.entity.Product;
import com.stayrascal.cloud.product.domain.factory.ProductFactory;
import com.stayrascal.cloud.product.domain.mapper.ProductDtoMapper;
import com.stayrascal.cloud.product.infrastructure.persistence.JpaProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Transactional
public class ProductFacade {
    private final ProductFactory productFactory;
    private final JpaProductRepository productRepository;
    private final ProductDtoMapper mapper;

    @Autowired
    public ProductFacade(ProductFactory productFactory, JpaProductRepository productRepository, ProductDtoMapper mapper) {
        this.productFactory = productFactory;
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    public ProductDto getProductById(String id) {
        Product product = getProduct(id);
        return mapper.productToDto(product);
    }

    private Product getProduct(String id) {
        return productRepository.ofId(id).orElseThrow(() ->
                new NotFoundException(ErrorCode.INTERNAL_ERROR, "Product of id {} not found", id));
    }

    public String createProduct(CreateProductCommand command) {
        return productRepository.insert(mapper.productFromDto(productFactory.createProduct(command)));
    }

    public ProductDto updateProduct(String productId, UpdateProductCommand command) {
        Product product = getProduct(productId);
        return mapper.productToDto(product);
    }

    public ProductDto delete(String id) {
        Product product = getProduct(id);
        productRepository.delete(product);
        return mapper.productToDto(product);
    }

    public List<ProductDto> listProducts(SortQuery sortQuery, Map<String, String> queryMaps) {
        List<Product> products = productRepository.listProducts(sortQuery, queryMaps);
        return products.stream().map(mapper::productToDto).collect(Collectors.toList());
    }
}
