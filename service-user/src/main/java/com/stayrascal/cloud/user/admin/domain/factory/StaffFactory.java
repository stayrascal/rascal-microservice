package com.stayrascal.cloud.user.admin.domain.factory;

import com.stayrascal.cloud.common.jpa.UniqueKeyGenerator;
import com.stayrascal.cloud.common.mapper.DefaultMapper;
import com.stayrascal.cloud.user.admin.contract.StaffStatus;
import com.stayrascal.cloud.user.admin.contract.command.CreateStaffCommand;
import com.stayrascal.cloud.user.admin.domain.entity.Staff;
import com.stayrascal.cloud.user.admin.domain.vo.StaffAuthorization;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class StaffFactory {
    private final UniqueKeyGenerator uniqueKeyGenerator;
    private final DefaultMapper commonMapper;

    @Autowired
    public StaffFactory(UniqueKeyGenerator uniqueKeyGenerator) {
        this.uniqueKeyGenerator = uniqueKeyGenerator;
        this.commonMapper = DefaultMapper.builder()
                .register(CreateStaffCommand.class, Staff.class)
                .build();
    }

    public Staff create(CreateStaffCommand command) {
        Staff staff = commonMapper.map(command, Staff.class);
        staff.setId(uniqueKeyGenerator.generateKey());
        staff.setTimeCreated(DateTime.now().toDate());
        staff.setStatus(StaffStatus.ENABLED);

        staff.setAuthorizations(command.getAuthorizations().stream().map(dto -> {
            StaffAuthorization staffAuthorization = new StaffAuthorization();
            staffAuthorization.setOrganizationId(dto.getOrganizationId());
            staffAuthorization.setRoleId(dto.getRoleId());
            return staffAuthorization;
        }).collect(Collectors.toList()));
        return staff;
    }
}
