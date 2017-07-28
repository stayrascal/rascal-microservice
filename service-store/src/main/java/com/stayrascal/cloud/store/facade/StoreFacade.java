package com.stayrascal.cloud.store.facade;

import com.stayrascal.cloud.common.constant.ErrorCode;
import com.stayrascal.cloud.common.contract.enumeration.CommonStatus;
import com.stayrascal.cloud.common.contract.query.SortQuery;
import com.stayrascal.cloud.common.jersey.exception.NotFoundException;
import com.stayrascal.cloud.store.contract.command.CreateStoreCommand;
import com.stayrascal.cloud.store.contract.command.UpdateStoreCommand;
import com.stayrascal.cloud.store.contract.dto.StoreDto;
import com.stayrascal.cloud.store.domain.entity.Store;
import com.stayrascal.cloud.store.domain.factory.StoreFactory;
import com.stayrascal.cloud.store.domain.mapper.StoreDtoMapper;
import com.stayrascal.cloud.store.domain.repository.StoreRepository;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional
public class StoreFacade {
    private final StoreFactory storeFactory;
    private final StoreDtoMapper storeDtoMapper;
    private final StoreRepository storeRepository;

    @Autowired
    public StoreFacade(StoreFactory storeFactory, StoreDtoMapper storeDtoMapper, StoreRepository storeRepository) {
        this.storeFactory = storeFactory;
        this.storeDtoMapper = storeDtoMapper;
        this.storeRepository = storeRepository;
    }

    public StoreDto getStore(String id) {
        return storeDtoMapper.storeToDto(getStoreById(id));
    }

    Store getStoreById(String id) {
        return storeRepository.ofId(id).orElseThrow(() ->
                new NotFoundException(ErrorCode.INTERNAL_ERROR, "Store of id {} not found", id)
        );
    }

    public String createStore(CreateStoreCommand createStoreCommand) {

        return storeRepository.insert(storeDtoMapper.storeFromDto(storeFactory.createOrder(createStoreCommand)));
    }

    public StoreDto updateStore(String storeId, UpdateStoreCommand command) {
        Store store = getStoreById(storeId);
        if (!Strings.isNullOrEmpty(command.getName())) {
            store.setName(command.getName());
        }
        if (!Strings.isNullOrEmpty(command.getContactNumber())) {
            store.setContactNumber(command.getContactNumber());
        }
        if (!Strings.isNullOrEmpty(command.getShopTime())) {
            store.setShopTime(command.getShopTime());
        }
        if (!Strings.isNullOrEmpty(command.getClosingTime())) {
            store.setClosingTime(command.getClosingTime());
        }
        return storeDtoMapper.storeToDto(storeRepository.update(store));
    }

    public long countStores(String provinceId, String cityId, String districtId, CommonStatus status) {
        return storeRepository.countStores(provinceId, cityId, districtId, status);
    }

    public List<StoreDto> listStores(SortQuery sortQuery, String provinceId, String cityId, String districtId, CommonStatus status) {
        List<Store> stores = storeRepository.listStores(sortQuery, provinceId, cityId, districtId, status);
        return stores.stream().map(storeDtoMapper::storeToDto).collect(Collectors.toList());
    }
}
