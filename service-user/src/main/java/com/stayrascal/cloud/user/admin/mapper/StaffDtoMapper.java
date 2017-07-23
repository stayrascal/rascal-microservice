package com.stayrascal.cloud.user.admin.mapper;

import com.stayrascal.cloud.common.mapper.DefaultMapper;
import com.stayrascal.cloud.user.admin.domain.entity.Staff;
import com.stayrascal.cloud.user.admin.domain.vo.StaffAuthorization;
import com.stayrascal.cloud.common.contract.auth.Authorization;

import com.stayrscal.cloud.user.admin.contract.dto.StaffDto;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class StaffDtoMapper extends DefaultMapper {
    public StaffDtoMapper() {
        register(StaffDto.class, Staff.class);
    }

    public Staff staffFromDto(StaffDto staffDto) {
        Staff staff = map(staffDto, Staff.class);
        staff.setAuthorizations(staffDto.getAuthorizations().stream().map(
                this::staffAuthorizationFromDto
        ).collect(Collectors.toList()));
        return staff;
    }

    public StaffDto staffToDto(Staff staff) {
        StaffDto staffDto = map(staff, StaffDto.class);
        staffDto.setAuthorizations(staff.getAuthorizations().stream().map(
                this::staffAuthorizationToDto
        ).collect(Collectors.toList()));
        return staffDto;
    }

    private Authorization staffAuthorizationToDto(StaffAuthorization staffAuthorization) {
        return map(staffAuthorization, Authorization.class);
    }

    private StaffAuthorization staffAuthorizationFromDto(Authorization authorization) {
        return map(authorization, StaffAuthorization.class);
    }
}
