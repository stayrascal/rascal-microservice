package com.stayrascal.cloud.user.admin.facade;

import com.stayrascal.cloud.common.constant.ErrorCode;
import com.stayrascal.cloud.common.contract.enumeration.SortType;
import com.stayrascal.cloud.common.ddd.EventSender;
import com.stayrascal.cloud.common.jersey.exception.NotFoundException;
import com.stayrascal.cloud.user.admin.contract.command.CreateStaffCommand;
import com.stayrascal.cloud.user.admin.contract.dto.StaffDto;
import com.stayrascal.cloud.user.admin.contract.event.StaffCreatedEvent;
import com.stayrascal.cloud.user.admin.domain.entity.Staff;
import com.stayrascal.cloud.user.admin.domain.factory.StaffFactory;
import com.stayrascal.cloud.user.admin.infrastructure.persistence.JpaStaffRepository;
import com.stayrascal.cloud.user.admin.mapper.StaffDtoMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional
public class StaffFacade {

    private StaffFactory staffFactory;
    private EventSender eventSender;
    private final JpaStaffRepository staffRepository;
    private final StaffDtoMapper staffDtoMapper;

    @Autowired
    public StaffFacade(StaffFactory staffFactory, EventSender eventSender, JpaStaffRepository staffRepository, StaffDtoMapper staffDtoMapper) {
        this.staffFactory = staffFactory;
        this.eventSender = eventSender;
        this.staffRepository = staffRepository;
        this.staffDtoMapper = staffDtoMapper;
    }

    public StaffDto getStaff(String id) {
        Staff staff = staffRepository.ofId(id).orElseThrow(() ->
                new NotFoundException(ErrorCode.INTERNAL_ERROR, "Staff of id {} not found", id));
        return staffDtoMapper.staffToDto(staff);
    }

    public String createStaff(CreateStaffCommand command) {
        Staff staff = staffFactory.create(command);
        staffRepository.insert(staff);

        StaffCreatedEvent staffCreatedEvent = new StaffCreatedEvent(staff.getId());
        eventSender.send(staffCreatedEvent);

        return staff.getId();
    }

    public Long countStaffs(String organizationId, String roleId) {
        return staffRepository.countStaffs(organizationId, roleId);
    }

    public List<StaffDto> listStaffs(SortType sortType, String sortBy, Integer pageSize, Integer pageIndex,
                                     String organizationId, String roleId) {
        List<Staff> staffs = staffRepository.listStaffs(sortType, sortBy, pageSize, pageIndex, organizationId, roleId);
        return staffs.stream().map(staffDtoMapper::staffToDto).collect(Collectors.toList());
    }
}
