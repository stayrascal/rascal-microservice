package com.stayrascal.cloud.user.member.facade;


import com.stayrascal.cloud.common.constant.ErrorCode;
import com.stayrascal.cloud.common.ddd.EventSender;
import com.stayrascal.cloud.common.jersey.exception.NotFoundException;
import com.stayrascal.cloud.user.auth.facade.AuthFacade;
import com.stayrascal.cloud.user.member.domain.entity.Member;
import com.stayrascal.cloud.user.member.domain.factory.MemberFactory;
import com.stayrascal.cloud.user.member.domain.repository.MemberRepository;
import com.stayrascal.cloud.user.member.mapper.MemberDtoMapper;
import com.stayrascal.clould.common.contract.query.SortQuery;
import com.stayrscal.cloud.user.member.contract.command.CreateMemberCommand;
import com.stayrscal.cloud.user.member.contract.command.UpdateMemberCommand;
import com.stayrscal.cloud.user.member.contract.dto.MemberDto;
import com.stayrscal.cloud.user.member.contract.event.MemberCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Component
@Transactional
public class MemberFacade {
    private final MemberDtoMapper memberDtoMapper;
    private final MemberFactory memberFactory;
    private final MemberRepository memberRepository;
    private final EventSender eventSender;
    private final AuthFacade authFacade;

    @Autowired
    public MemberFacade(MemberRepository memberRepository,
                        MemberFactory memberFactory,
                        EventSender eventSender,
                        AuthFacade authFacade) {
        this.memberRepository = memberRepository;
        this.memberFactory = memberFactory;
        this.eventSender = eventSender;
        this.authFacade = authFacade;
        memberDtoMapper = new MemberDtoMapper();
    }

    public MemberDto getMemberById(String memberId) {
        Optional<Member> user = memberRepository.ofId(memberId);
        return memberDtoMapper.map(user
                        .orElseThrow(() ->
                                new NotFoundException(ErrorCode.INTERNAL_ERROR, "Member {} not found", memberId)),
                MemberDto.class);
    }

    public String createMember(CreateMemberCommand command) {
        Member member = memberFactory.create(command);

        MemberCreatedEvent memberCreatedEvent = new MemberCreatedEvent(member.getId());
        eventSender.send(memberCreatedEvent);

        memberRepository.insert(member);
        return member.getId();
    }

    public void updateMember(String memberId, UpdateMemberCommand command) {
        Member member = memberRepository.ofId(memberId).orElseThrow(() ->
                new NotFoundException(ErrorCode.INTERNAL_ERROR, "Member {} not found", memberId));
        checkAndUpdate(command.getNickname(), member::setNickname);
        checkAndUpdate(command.getGender(), member::setGender);
        checkAndUpdate(command.getBirthday(), member::setBirthday);
        checkAndUpdate(command.getEmail(), member::setEmail);
        checkAndUpdate(command.getMobile(), member::setMobile);
        memberRepository.update(member);
    }

    private <T> void checkAndUpdate(T em, Consumer<T> consumer) {
        Optional.ofNullable(em).ifPresent(consumer);
    }

    public Long countMembers(String mobile) {
        return memberRepository.count(mobile);
    }

    public List<MemberDto> listMembers(String mobile, SortQuery sortQuery) {
        return memberRepository.findMembers(mobile, sortQuery)
                .stream()
                .map(member -> memberDtoMapper.map(member, MemberDto.class))
                .collect(Collectors.toList());
    }
}

