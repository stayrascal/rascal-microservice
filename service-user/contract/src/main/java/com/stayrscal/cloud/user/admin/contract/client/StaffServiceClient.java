package com.stayrscal.cloud.user.admin.contract.client;

import com.stayrascal.clould.common.contract.QueryMap;
import com.stayrascal.clould.common.contract.enumeration.SortType;
import com.stayrascal.clould.common.contract.result.CreatedResult;
import com.stayrascal.clould.common.contract.result.PageResult;

import com.stayrscal.cloud.user.admin.contract.command.CreateStaffCommand;
import com.stayrscal.cloud.user.admin.contract.dto.StaffDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import javax.ws.rs.core.MediaType;

@FeignClient(value = "service-user")
public interface StaffServiceClient {

    @RequestMapping(method = RequestMethod.GET, value = "/rest/staffs/{id}", consumes = MediaType.APPLICATION_JSON)
    StaffDto getStaff(@PathVariable("id") String userId);

    @RequestMapping(method = RequestMethod.GET, value = "/rest/staffs", consumes = MediaType.APPLICATION_JSON)
    CreatedResult createStaff(CreateStaffCommand command);

    @RequestMapping(method = RequestMethod.GET, value = "/rest/staffs", consumes = MediaType.APPLICATION_JSON)
    PageResult listStaffs(@RequestParam("sort_type") SortType sortType,
                          @RequestParam("sort_by") String sortBy,
                          @RequestParam("page_size") Integer pageSize,
                          @RequestParam("page_index") Integer pageIndex,
                          @RequestParam Map<String, String> queryMap);

    default PageResult listStaffs(Integer pageSize, Integer pageIndex, Map<String, String> queryMap) {
        return listStaffs(SortType.ASC, "timeCreated", pageSize, pageIndex, queryMap);
    }

    default PageResult listStaffs(SortType sortType, String sortBy, Integer pageSize, Integer pageIndex, String organizationId, String roleId) {
        Map<String, String> queryMap = QueryMap.builder()
                .put("organization_id", organizationId)
                .put("role_id", roleId)
                .build();
        return listStaffs(sortType, sortBy, pageSize, pageIndex, queryMap);
    }
}
