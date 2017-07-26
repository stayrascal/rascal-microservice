package com.stayrascal.cloud.user.auth.contract.client;

import com.stayrascal.cloud.common.contract.QueryMap;
import com.stayrascal.cloud.common.contract.enumeration.SortType;
import com.stayrascal.cloud.common.contract.result.CreatedResult;
import com.stayrascal.cloud.common.contract.result.PageResult;
import com.stayrascal.cloud.user.auth.contract.command.CreateRoleCommand;
import com.stayrascal.cloud.user.auth.contract.command.CreateRolePermissionCommand;
import com.stayrascal.cloud.user.auth.contract.dto.RoleDto;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import javax.ws.rs.core.MediaType;

@FeignClient(value = "korprulu-user")
public interface RoleServiceClient {
    @RequestMapping(method = RequestMethod.GET, value = "/rest/roles/{id}",
            consumes = MediaType.APPLICATION_JSON)
    RoleDto getRole(@PathVariable("id") String roleId);

    @RequestMapping(method = RequestMethod.POST, value = "/rest/roles",
            consumes = MediaType.APPLICATION_JSON)
    CreatedResult createRole(CreateRoleCommand command);

    @RequestMapping(method = RequestMethod.GET, value = "/rest/roles",
            consumes = MediaType.APPLICATION_JSON)
    PageResult listRoles(@RequestParam("sort_type") SortType sortType,
                         @RequestParam("sort_by") String sortBy,
                         @RequestParam("page_size") Integer pageSize,
                         @RequestParam("page_index") Integer pageIndex,
                         @RequestParam Map<String, String> queryMap);

    default PageResult listRoles(Integer pageSize, Integer pageIndex, Map<String, String> queryMap) {
        return listRoles(SortType.ASC, "timeCreated", pageSize, pageIndex, queryMap);
    }

    default PageResult listRoles(SortType sortType, String sortBy, Integer pageSize, Integer pageIndex, String name) {
        return listRoles(sortType, sortBy, pageSize, pageIndex, QueryMap.builder().put("name", name).build());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/rest/roles/{id}/permissions",
            consumes = MediaType.APPLICATION_JSON)
    CreatedResult createRolePermission(@PathVariable("id") String roleId, CreateRolePermissionCommand command);

    @RequestMapping(method = RequestMethod.DELETE, value = "/rest/roles/{role_id}/permissions/{permission_id}")
    void deleteRolePermission(@PathVariable("role_id") String roleId,
                              @PathVariable("permission_id") String permissionId);
}
