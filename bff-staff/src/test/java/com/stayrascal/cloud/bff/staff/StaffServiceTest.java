package com.stayrascal.cloud.bff.staff;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;

import com.stayrascal.cloud.address.contract.client.AddressServiceClient;
import com.stayrascal.cloud.address.contract.dto.AddressDto;
import com.stayrascal.cloud.bff.staff.response.ClerkResponse;
import com.stayrascal.cloud.common.contract.result.PageResult;
import com.stayrascal.cloud.mapping.contract.client.MappingServiceClient;
import com.stayrascal.cloud.user.admin.contract.client.StaffServiceClient;
import com.stayrascal.cloud.user.admin.contract.dto.StaffDto;
import com.stayrascal.cloud.user.auth.contract.AuthenticationType;
import com.stayrascal.cloud.user.auth.contract.client.AuthServiceClient;
import com.stayrascal.cloud.user.auth.contract.dto.AuthenticationDto;

import com.exmertec.dummie.Dummie;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class StaffServiceTest {

    @InjectMocks
    private StaffService staffService;

    @Mock
    private StaffServiceClient staffClient;

    @Mock
    private AuthServiceClient authClient;

    @Mock
    private MappingServiceClient mappingClient;

    @Mock
    private AddressServiceClient addressClient;

    @Test
    public void shouldReturnDatasIncludeClerkIdWhenGivenAnValidStoreId() throws Exception {
        final PageResult result = Dummie.create(PageResult.class);
        result.setItems(Lists.newArrayList(Dummie.create(StaffDto.class)));
        given(staffClient.listStaffs(anyInt(), anyInt(), any())).willReturn(result);

        final PageResult authResult = Dummie.create(PageResult.class);
        authResult.setTotalCount(1L);
        final AuthenticationDto authenticationDto = Dummie.create(AuthenticationDto.class);
        authenticationDto.setAuthenticationType(AuthenticationType.PASSWORD);
        authResult.setItems(Lists.newArrayList(authenticationDto));
        given(authClient.listAuthentications(anyInt(), anyInt(), any())).willReturn(authResult);

        final PageResult mappingResult = Dummie.create(PageResult.class);
        mappingResult.setItems(Lists.newArrayList(1L));
        given(mappingClient.retrieveAddressIds(anyString())).willReturn(mappingResult);

        given(addressClient.get(anyLong())).willReturn(new AddressDto());

        String storeId = "storeId";
        final PageResult pageResult = staffService.listClerk(storeId, 10, 0);

        final List<ClerkResponse> items = pageResult.getItems();
        assertThat(items).isNotNull();
        assertThat(items).isNotEmpty();
        assertThat(items.get(0).getLoginId()).isNotNull();
    }
}
