package com.stayrascal.cloud.user.member.mapper;

import com.stayrascal.cloud.common.mapper.DefaultMapper;
import com.stayrascal.cloud.user.member.domain.entity.Member;
import com.stayrascal.cloud.user.member.infrastructure.persistence.po.MemberPo;
import org.springframework.stereotype.Component;

@Component
public class MemberPoMapper extends DefaultMapper {
    public MemberPoMapper() {
        register(MemberPo.class, Member.class);
    }

    public Member memberFromPo(MemberPo memberPo) {
        return map(memberPo, Member.class);
    }
}
