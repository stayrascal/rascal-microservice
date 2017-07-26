package com.stayrascal.cloud.bff.functional.resource.mock;

import com.stayrascal.cloud.common.contract.enumeration.SortType;
import com.stayrascal.cloud.common.contract.result.CreatedResult;
import com.stayrascal.cloud.common.contract.result.PageResult;
import com.stayrascal.cloud.user.member.contract.client.MemberServiceClient;
import com.stayrascal.cloud.user.member.contract.command.CreateMemberCommand;
import com.stayrascal.cloud.user.member.contract.dto.MemberDto;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MockMemberServiceClient implements MemberServiceClient {
    private String json = "";

    @Override
    public MemberDto getMember(String userId) {
        return MockUtils.fromJson(json, MemberDto.class);
    }

    @Override
    public CreatedResult createMember(CreateMemberCommand command) {
        return null;
    }

    @Override
    public PageResult listMembers(SortType sortType, String sortBy, Integer pageSize, Integer pageIndex, Map<String, String> queryMap) {
        return null;
    }

    public void setJson(String json) {
        this.json = json;
    }
}
