package com.stayrscal.cloud.user.member.contract.client;

import com.stayrascal.clould.common.contract.QueryMap;
import com.stayrascal.clould.common.contract.enumeration.SortType;
import com.stayrascal.clould.common.contract.result.CreatedResult;
import com.stayrascal.clould.common.contract.result.PageResult;

import com.stayrscal.cloud.user.member.contract.command.CreateMemberCommand;
import com.stayrscal.cloud.user.member.contract.dto.MemberDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import javax.ws.rs.core.MediaType;

@FeignClient(value = "service-user")
public interface MemberServiceClient {
    @RequestMapping(method = RequestMethod.GET, value = "/rest/members/{id}", consumes = MediaType.APPLICATION_JSON)
    MemberDto getMember(@PathVariable("id") String userId);

    @RequestMapping(method = RequestMethod.POST, value = "/rest/members", consumes = MediaType.APPLICATION_JSON)
    CreatedResult createMember(CreateMemberCommand command);

    @RequestMapping(method = RequestMethod.GET, value = "/rest/members", consumes = MediaType.APPLICATION_JSON)
    PageResult listMembers(@RequestParam("sort_type") SortType sortType,
                           @RequestParam("sort_by") String sortBy,
                           @RequestParam("page_size") Integer pageSize,
                           @RequestParam("page_index") Integer pageIndex,
                           @RequestParam Map<String, String> queryMap);

    default PageResult listMembers(Integer pageSize, Integer pageIndex, Map<String, String> queryMap) {
        return listMembers(SortType.ASC, "timeCreated", pageSize, pageIndex, queryMap);
    }

    default PageResult listMembers(SortType sortType, String sortBy, Integer pageSize, Integer pageIndex,
                                   String mobile) {
        return listMembers(sortType, sortBy, pageSize, pageIndex, QueryMap.builder().put("mobile", mobile).build());
    }
}
