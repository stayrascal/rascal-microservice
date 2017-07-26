package com.stayrascal.cloud.bff.staff;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;

import com.stayrascal.cloud.bff.staff.response.ClerkResponse;
import com.stayrascal.cloud.common.contract.result.PageResult;
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

        String storeId = "storeId";
        final PageResult pageResult = staffService.listClerk(storeId, 10, 0);

        final List<ClerkResponse> items = pageResult.getItems();
        assertThat(items).isNotNull();
        assertThat(items).isNotEmpty();
        assertThat(items.get(0).getLoginId()).isNotNull();
    }
}
