package com.stayrascal.cloud.user.member.domain.repository;

import com.stayrascal.cloud.common.ddd.Repository;
import com.stayrascal.cloud.user.member.domain.entity.Member;
import com.stayrascal.cloud.common.contract.query.SortQuery;

import java.util.List;

public interface MemberRepository extends Repository<Member, String> {
    List<Member> findMembers(String mobile, SortQuery sortQuery);

    long count(String mobile);
}
