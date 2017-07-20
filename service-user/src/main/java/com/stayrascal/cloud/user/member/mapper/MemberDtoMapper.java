package com.stayrascal.cloud.user.member.mapper;

import com.stayrascal.cloud.common.mapper.DefaultMapper;
import com.stayrascal.cloud.user.member.domain.entity.Member;
import com.stayrscal.cloud.user.member.contract.command.CreateMemberCommand;
import com.stayrscal.cloud.user.member.contract.dto.MemberDto;
import org.springframework.stereotype.Component;

@Component
public class MemberDtoMapper extends DefaultMapper {
    public MemberDtoMapper() {
        register(MemberDto.class, Member.class);
        register(CreateMemberCommand.class, Member.class);
    }
}
