package com.stayrascal.cloud.user.admin.domain.repository;

import com.stayrascal.cloud.common.ddd.Repository;
import com.stayrascal.cloud.user.admin.domain.entity.Staff;
import com.stayrascal.clould.common.contract.enumeration.SortType;

import java.util.List;

public interface StaffRepository extends Repository<Staff, String> {
    Long countStaffs(String organizationId, String roleId);

    List<Staff> listStaffs(SortType sortType, String sortBy, Integer pageSize, Integer pageIndex,
                           String organizationId, String roleId);
}
