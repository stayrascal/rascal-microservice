package com.stayrascal.cloud.user.admin.mapper;

import com.stayrascal.cloud.common.mapper.DefaultMapper;
import com.stayrascal.cloud.user.admin.domain.entity.Staff;
import com.stayrascal.cloud.user.admin.domain.vo.StaffAuthorization;
import com.stayrascal.cloud.user.admin.infrastructure.persistence.po.StaffAuthorizationPo;
import com.stayrascal.cloud.user.admin.infrastructure.persistence.po.StaffPo;

import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class StaffPoMapper extends DefaultMapper {
    public StaffPoMapper() {
        register(StaffPo.class, Staff.class);
        register(StaffAuthorizationPo.class, StaffAuthorization.class);
    }

    public Staff staffFromPo(StaffPo staffPo) {
        Staff staff = map(staffPo, Staff.class);
        staff.setAuthorizations(staffPo.getAuthorizations().stream()
                .map(this::staffAuthorizationFromPO).collect(Collectors.toList()));
        return staff;
    }

    private StaffAuthorization staffAuthorizationFromPO(StaffAuthorizationPo staffAuthorizationPo) {
        return map(staffAuthorizationPo, StaffAuthorization.class);
    }

    private StaffAuthorizationPo staffAuthorizationToPO(String staffId, StaffAuthorization staffAuthorization) {
        final StaffAuthorizationPo authorization = map(staffAuthorization, StaffAuthorizationPo.class);
        authorization.setStaffId(staffId);
        return authorization;
    }

    public StaffPo staffToPo(Staff staff) {
        StaffPo staffPo = map(staff, StaffPo.class);
        staffPo.setAuthorizations(staff.getAuthorizations().stream()
                .map(authorization -> staffAuthorizationToPO(staff.getId(), authorization))
                .collect(Collectors.toList()));
        return staffPo;
    }
}
