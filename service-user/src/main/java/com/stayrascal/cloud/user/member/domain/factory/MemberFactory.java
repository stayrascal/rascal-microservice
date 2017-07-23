package com.stayrascal.cloud.user.member.domain.factory;

import com.stayrascal.cloud.common.jpa.UniqueKeyGenerator;
import com.stayrascal.cloud.common.mapper.DefaultMapper;
import com.stayrascal.cloud.user.member.domain.entity.Member;

import com.stayrscal.cloud.user.member.contract.MemberStatus;
import com.stayrscal.cloud.user.member.contract.command.CreateMemberCommand;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberFactory {
    private final UniqueKeyGenerator uniqueKeyGenerator;
    private final DefaultMapper commandMapper;

    @Autowired
    public MemberFactory(UniqueKeyGenerator uniqueKeyGenerator) {
        this.uniqueKeyGenerator = uniqueKeyGenerator;
        this.commandMapper = DefaultMapper.builder().register(CreateMemberCommand.class, Member.class).build();
    }

    public Member create(CreateMemberCommand command) {
        Member member = commandMapper.map(command, Member.class);
        member.setId(uniqueKeyGenerator.generateKey());
        member.setTimeCreated(DateTime.now().toDate());
        member.setStatus(MemberStatus.ENABLED);
        return member;
    }
}
