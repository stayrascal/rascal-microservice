package com.stayrascal.cloud.mapping.infrastructure.persistence;

import com.stayrascal.cloud.mapping.infrastructure.persistence.po.StaffAddressMappingPo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaStaffAddressMappingRepository extends CrudRepository<StaffAddressMappingPo, String> {
    List<StaffAddressMappingPo> findByUserId(String userId);

    List<StaffAddressMappingPo> findByAddressId(String addressId);

    List<StaffAddressMappingPo> findByUserIdAndAddressId(String userId, String addressId);
}
