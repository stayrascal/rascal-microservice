package com.stayrascal.cloud.product.facade;

import com.stayrascal.cloud.common.constant.ErrorCode;
import com.stayrascal.cloud.common.contract.query.SortQuery;
import com.stayrascal.cloud.common.jersey.exception.NotFoundException;
import com.stayrascal.cloud.product.contract.command.CreateStoreProductCommand;
import com.stayrascal.cloud.product.contract.command.UpdateStoreProductCommand;
import com.stayrascal.cloud.product.contract.command.UpdateStoreProductItemCommand;
import com.stayrascal.cloud.product.contract.dto.StoreProductDto;
import com.stayrascal.cloud.product.domain.entity.StoreProduct;
import com.stayrascal.cloud.product.domain.entity.StoreProductItem;
import com.stayrascal.cloud.product.domain.factory.StoreProductFactory;
import com.stayrascal.cloud.product.domain.mapper.StoreProductDtoMapper;
import com.stayrascal.cloud.product.infrastructure.persistence.JpaStoreProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class StoreProductFacade {
    private final JpaStoreProductRepository repository;
    private final StoreProductDtoMapper mapper;
    private final StoreProductFactory factory;

    @Autowired
    public StoreProductFacade(JpaStoreProductRepository repository, StoreProductDtoMapper mapper, StoreProductFactory factory) {
        this.repository = repository;
        this.mapper = mapper;
        this.factory = factory;
    }

    public StoreProductDto getStoreProductById(String id) {
        StoreProduct storeProduct = getStoreProduct(id);
        return mapper.storeProductToDto(storeProduct);
    }

    private StoreProduct getStoreProduct(String id) {
        return repository.ofId(id).orElseThrow(() ->
                new NotFoundException(ErrorCode.INTERNAL_ERROR, "StoreProduct of id {} not found", id));
    }

    public List<StoreProductDto> listStoreProducts(SortQuery sortQuery, Map<String, String> queryMaps) {
        List<StoreProduct> storeProducts = repository.listStoreProducts(sortQuery, queryMaps);
        return storeProducts.stream().map(mapper::storeProductToDto).collect(Collectors.toList());
    }

    public String createStoreProduct(CreateStoreProductCommand command) {
        return repository.insert(factory.createStoreProduct(command));
    }

    public StoreProductDto updateStoreProduct(String id, UpdateStoreProductCommand command) {
        StoreProduct storeProduct = getStoreProduct(id);
        List<StoreProductItem> items = storeProduct.getItems();
        if (command.getStatus() != null) {
            storeProduct.setStatus(command.getStatus());
        }
        if (command.getUpdateItemCommands() != null && command.getUpdateItemCommands().size() > 0) {
            for (Map.Entry<String, UpdateStoreProductItemCommand> entry : command.getUpdateItemCommands().entrySet()) {
                UpdateStoreProductItemCommand value = entry.getValue();
                for (StoreProductItem item : items) {
                    if (item.getItemId() == entry.getKey()) {
                        if (value.getPrice() != null) {
                            item.setPrice(value.getPrice());
                        }
                        if (value.getQuantity() != null) {
                            item.setQuantity(value.getQuantity());
                        }
                    }
                }
            }
        }
        return mapper.storeProductToDto(repository.update(storeProduct));
    }

    public StoreProductDto updateStoreProductItem(String id, String itemId, UpdateStoreProductItemCommand command) {
        StoreProduct storeProduct = getStoreProduct(id);
        for (StoreProductItem item : storeProduct.getItems()) {
            if (item.getId().equals(itemId)) {
                if (command.getQuantity() != null) {
                    item.setQuantity(command.getQuantity());
                }
                if (command.getPrice() != null) {
                    item.setPrice(command.getPrice());
                }
            }
        }
        return mapper.storeProductToDto(repository.update(storeProduct));
    }
}
