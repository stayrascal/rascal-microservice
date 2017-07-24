package com.stayrascal.cloud.organization.contract.client;

import com.stayrascal.cloud.common.contract.QueryMap;
import com.stayrascal.cloud.common.contract.enumeration.SortType;
import com.stayrascal.cloud.common.contract.result.CreatedResult;
import com.stayrascal.cloud.common.contract.result.PageResult;
import com.stayrascal.cloud.organization.contract.command.CreateOrganizationCommand;
import com.stayrascal.cloud.organization.contract.command.UpdateOrganizationCommand;
import com.stayrascal.cloud.organization.contract.dto.OrganizationDto;
import com.stayrascal.cloud.organization.contract.enumeration.OrganizationType;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import javax.ws.rs.core.MediaType;

@FeignClient(name = "service-organization")
public interface OrganizationServiceClient {
    @RequestMapping(method = RequestMethod.GET, value = "/rest/organizations/{id}",
            consumes = MediaType.APPLICATION_JSON)
    OrganizationDto getOrganization(@PathVariable("id") String organizationId);

    @RequestMapping(method = RequestMethod.POST, value = "/rest/organizations",
            consumes = MediaType.APPLICATION_JSON)
    CreatedResult createOrganization(CreateOrganizationCommand command);

    @RequestMapping(method = RequestMethod.PUT, path = "/rest/organizations/{id}",
            consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    void updateOrganization(@PathVariable("id") String id, UpdateOrganizationCommand updateCommand);

    @RequestMapping(method = RequestMethod.GET, value = "/rest/organizations",
            consumes = MediaType.APPLICATION_JSON)
    PageResult listOrganizations(@RequestParam("sort_type") SortType sortType,
                                 @RequestParam("sort_by") String sortBy,
                                 @RequestParam("page_size") Integer pageSize,
                                 @RequestParam("page_index") Integer pageIndex,
                                 @RequestParam Map<String, String> queryMap);

    default PageResult listOrganizations(Integer pageSize, Integer pageIndex, Map<String, String> queryMap) {
        return listOrganizations(SortType.ASC, "timeCreated", pageSize, pageIndex, queryMap);
    }

    default PageResult listOrganizations(SortType sortType, String sortBy, Integer pageSize, Integer pageIndex,
                                         String name, OrganizationType type) {
        Map<String, String> queryMap = QueryMap.builder()
                .put("name", name)
                .put("type", type, Enum::name)
                .build();
        return listOrganizations(sortType, sortBy, pageSize, pageIndex, queryMap);
    }
}
