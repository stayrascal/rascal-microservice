package com.stayrascal.cloud.bff.staff;

import com.stayrascal.cloud.bff.staff.request.CreateClerkCommand;
import com.stayrascal.cloud.bff.staff.response.ClerkResponse;
import com.stayrascal.cloud.common.contract.QueryMap;
import com.stayrascal.cloud.common.contract.auth.Authorization;
import com.stayrascal.cloud.common.contract.auth.IdentityType;
import com.stayrascal.cloud.common.contract.result.CreatedResult;
import com.stayrascal.cloud.common.contract.result.PageResult;
import com.stayrascal.cloud.common.mapper.DefaultMapper;
import com.stayrascal.cloud.user.admin.contract.client.StaffServiceClient;
import com.stayrascal.cloud.user.admin.contract.command.CreateStaffCommand;
import com.stayrascal.cloud.user.admin.contract.dto.StaffDto;
import com.stayrascal.cloud.user.auth.contract.AuthenticationType;
import com.stayrascal.cloud.user.auth.contract.client.AuthServiceClient;
import com.stayrascal.cloud.user.auth.contract.command.CreateAuthenticationCommand;
import com.stayrascal.cloud.user.auth.contract.dto.AuthenticationDto;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StaffService {
    public static final String CLERK_ROLE_ID = "store-click-role-id";
    private final AuthServiceClient authClient;
    private final StaffServiceClient staffClient;
    private final DefaultMapper mapper;

    @Autowired
    public StaffService(AuthServiceClient authClient, StaffServiceClient staffClient) {
        this.authClient = authClient;
        this.staffClient = staffClient;
        this.mapper = DefaultMapper.builder().build();
    }


    public PageResult listClerk(String storeId, Integer pageSize, Integer pageIndex) {
        final PageResult result = staffClient.listStaffs(pageSize, pageIndex, QueryMap.builder().put("store_id", storeId).build());
        final List<StaffDto> items = result.getItems();
        result.setItems(items.stream().map(this::mapToClerkResponse).collect(Collectors.toList()));
        return result;
    }

    public PageResult listClerk(Integer pageSize, Integer pageIndex) {
        final PageResult result = staffClient.listStaffs(pageSize, pageIndex, new HashMap<>());
        final List<StaffDto> items = result.getItems();
        result.setItems(items.stream().map(this::mapToClerkResponse).collect(Collectors.toList()));
        return result;
    }

    public CreatedResult createClerk(String storeId, CreateClerkCommand command) {
        final CreatedResult result = staffClient.createStaff(mapToCreateStaffCommand(storeId, command.getName(), command.getContact()));
        authClient.createAuthentication(generateCreateAuthenticationCommand(result.getId(), command.getPassword()));
        return result;

    }

    public ClerkResponse getClerk(String clerkId) {
        return mapToClerkResponse(staffClient.getStaff(clerkId));
    }

    private ClerkResponse mapToClerkResponse(StaffDto staffDto) {
        final ClerkResponse response = mapper.map(staffDto, ClerkResponse.class);
        final PageResult pageResult = authClient.listAuthentications(1, 0,
                QueryMap.builder()
                        .put("identity_type", IdentityType.STAFF.name())
                        .put("identity_id", staffDto.getId()).build());
        if (pageResult.getTotalCount() > 0L) {
            final List<AuthenticationDto> items = pageResult.getItems();
            response.setLoginId(items.stream()
                    .filter(item -> item.getAuthenticationType() == AuthenticationType.PASSWORD)
                    .findFirst()
                    .get().getAuthenticationName());
        }
        return response;
    }

    private CreateAuthenticationCommand generateCreateAuthenticationCommand(String staffId, String password) {
        CreateAuthenticationCommand command = new CreateAuthenticationCommand();
        command.setIdentityId(staffId);
        command.setIdentityType(IdentityType.STAFF);
        command.setAuthenticationName(generateLoginId());
        command.setAuthenticationType(AuthenticationType.PASSWORD);
        command.setPrimaryKeyValue(password);
        command.setPrimaryKeyExpireTime(DateTime.now().plusYears(100).toDate());
        return command;
    }

    private String generateLoginId() {
        final PageResult pageResult = staffClient.listStaffs(1, 0, Maps.newHashMap());
        return String.format("%05d", pageResult.getTotalCount().intValue());
    }

    private CreateStaffCommand mapToCreateStaffCommand(String storeId, String name, String contact) {
        CreateStaffCommand command = new CreateStaffCommand();
        command.setMobile(contact);
        command.setName(name);
        command.setAuthorizations(Lists.newArrayList(new Authorization(storeId, CLERK_ROLE_ID)));
        return command;
    }
}

