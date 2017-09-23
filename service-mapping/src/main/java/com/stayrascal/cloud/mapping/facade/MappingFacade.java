package com.stayrascal.cloud.mapping.facade;

import com.stayrascal.cloud.common.constant.ErrorCode;
import com.stayrascal.cloud.common.jersey.exception.NotFoundException;
import com.stayrascal.cloud.mapping.contract.command.CreateStaffAddressMappingCommand;
import com.stayrascal.cloud.mapping.contract.command.CreateStaffOrderMappingCommand;
import com.stayrascal.cloud.mapping.infrastructure.persistence.JpaStaffAddressMappingRepository;
import com.stayrascal.cloud.mapping.infrastructure.persistence.JpaStaffOrderMappingRepository;
import com.stayrascal.cloud.mapping.infrastructure.persistence.po.StaffAddressMappingPo;
import com.stayrascal.cloud.mapping.infrastructure.persistence.po.StaffOrderMappingPo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional
public class MappingFacade {

    private final JpaStaffAddressMappingRepository addressMappingRepository;
    private final JpaStaffOrderMappingRepository orderMappingRepository;

    @Autowired
    public MappingFacade(JpaStaffAddressMappingRepository addressMappingRepository, JpaStaffOrderMappingRepository orderMappingRepository) {
        this.addressMappingRepository = addressMappingRepository;
        this.orderMappingRepository = orderMappingRepository;
    }

    public List<Long> getAddressIdByStaffId(String staffId) {
        List<StaffAddressMappingPo> mappings = addressMappingRepository.findByUserId(staffId);
        if (mappings.size() > 0) {
            return mappings.stream().map(mapping -> mapping.getAddressId()).collect(Collectors.toList());
        } else {
            throw new NotFoundException(ErrorCode.INTERNAL_ERROR, "Address of user id {} not found", staffId);
        }
    }

    public List<String> getOrderIdByStaffId(String staffId) {
        List<StaffOrderMappingPo> mappings = orderMappingRepository.findByUserId(staffId);
        if (mappings.size() > 0) {
            return mappings.stream().map(mapping -> mapping.getOrderId()).collect(Collectors.toList());
        } else {
            throw new NotFoundException(ErrorCode.INTERNAL_ERROR, "Order of user id {} not found", staffId);
        }
    }

    public String addAddressMapping(CreateStaffAddressMappingCommand command) {
        return addressMappingRepository.save(new StaffAddressMappingPo(command.getUserId(), command.getAddressId())).getUserId();
    }

    public String addOrderMapping(CreateStaffOrderMappingCommand command) {
        return orderMappingRepository.save(new StaffOrderMappingPo(command.getUserId(), command.getOrderId())).getUserId();
    }
}
