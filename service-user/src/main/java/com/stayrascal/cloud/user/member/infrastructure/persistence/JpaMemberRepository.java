package com.stayrascal.cloud.user.member.infrastructure.persistence;

import static com.stayrascal.cloud.common.jpa.QueryHelper.list;

import com.stayrascal.cloud.common.jpa.BaseJpaRepository;
import com.stayrascal.cloud.user.member.domain.entity.Member;
import com.stayrascal.cloud.user.member.domain.repository.MemberRepository;
import com.stayrascal.cloud.user.member.infrastructure.persistence.po.MemberPo;
import com.stayrascal.cloud.user.member.mapper.MemberPoMapper;
import com.stayrascal.cloud.common.contract.query.SortQuery;

import com.exmertec.yaz.BaseDao;
import com.google.common.base.Strings;
import com.stayrscal.cloud.user.member.contract.MemberStatus;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;

@Component
public class JpaMemberRepository extends BaseJpaRepository implements MemberRepository {
    private final BaseDao<MemberPo> memberDao;
    private final MemberPoMapper memberPoMapper;

    @Autowired
    public JpaMemberRepository(EntityManager entityManager, MemberPoMapper memberPoMapper) {
        memberDao = new BaseDao<>(entityManager, MemberPo.class);
        this.memberPoMapper = memberPoMapper;
    }

    @Override
    public Optional<Member> ofId(String id) {
        MemberPo memberPo = memberDao.idEquals(id).querySingle();

        return Optional.ofNullable(memberPo).map(memberPoMapper::memberFromPo);
    }

    @Override
    public String insert(Member member) {
        MemberPo memberPo = memberPoMapper.map(member, MemberPo.class);
        memberPo.setTimeCreated(DateTime.now().toDate());
        memberPo.setStatus(MemberStatus.ENABLED);

        memberDao.save(memberPo);

        return memberPo.getId();
    }

    @Override
    public Member update(Member member) {
        MemberPo memberPo = memberPoMapper.map(member, MemberPo.class);
        return memberPoMapper.map(memberDao.update(memberPo), Member.class);
    }

    @Override
    public List<Member> findMembers(String mobile, SortQuery sortQuery) {
        return memberPoMapper.mapList(list(memberDao, sortQuery,
                BaseDao.field("mobile").eq(mobile).when(!Strings.isNullOrEmpty(mobile))), Member.class);
    }

    @Override
    public long count(String mobile) {
        return memberDao
                .where(BaseDao.field("mobile").eq(mobile).when(!Strings.isNullOrEmpty(mobile)))
                .count();
    }
}
