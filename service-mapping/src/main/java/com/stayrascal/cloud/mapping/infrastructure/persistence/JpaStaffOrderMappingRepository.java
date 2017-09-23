package com.stayrascal.cloud.mapping.infrastructure.persistence;

import com.stayrascal.cloud.mapping.infrastructure.persistence.po.StaffOrderMappingPo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaStaffOrderMappingRepository extends CrudRepository<StaffOrderMappingPo, String> {
    List<StaffOrderMappingPo> findByUserId(String userId);

    List<StaffOrderMappingPo> findByOrderId(String orderId);

    List<StaffOrderMappingPo> findByUserIdAndOrderId(String userId, String orderId);
}
