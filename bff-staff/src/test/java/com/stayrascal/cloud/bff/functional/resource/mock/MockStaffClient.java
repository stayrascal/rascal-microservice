package com.stayrascal.cloud.bff.functional.resource.mock;

import com.stayrascal.cloud.common.contract.enumeration.SortType;
import com.stayrascal.cloud.common.contract.result.CreatedResult;
import com.stayrascal.cloud.common.contract.result.PageResult;
import com.stayrascal.cloud.user.admin.contract.client.StaffServiceClient;
import com.stayrascal.cloud.user.admin.contract.command.CreateStaffCommand;
import com.stayrascal.cloud.user.admin.contract.dto.StaffDto;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MockStaffClient implements StaffServiceClient {
    @Override
    public StaffDto getStaff(String userId) {
        return null;
    }

    @Override
    public CreatedResult createStaff(CreateStaffCommand command) {
        return null;
    }

    @Override
    public PageResult listStaffs(SortType sortType, String sortBy, Integer pageSize, Integer pageIndex, Map<String, String> queryMap) {
        return null;
    }
}
