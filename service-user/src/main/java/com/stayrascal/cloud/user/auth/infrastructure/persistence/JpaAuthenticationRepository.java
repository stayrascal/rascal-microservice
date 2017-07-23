package com.stayrascal.cloud.user.auth.infrastructure.persistence;

import com.stayrascal.cloud.user.auth.domain.entity.Authentication;
import com.stayrascal.cloud.user.auth.domain.repository.AuthenticationRepository;
import com.stayrascal.cloud.user.auth.infrastructure.persistence.po.AuthenticationPo;
import com.stayrascal.cloud.user.auth.mapper.AuthenticationPoMapper;
import com.stayrascal.clould.common.contract.auth.IdentityType;
import com.stayrascal.clould.common.contract.enumeration.SortType;

import com.exmertec.yaz.BaseDao;
import com.exmertec.yaz.core.OrderType;
import com.exmertec.yaz.core.Query;
import com.stayrscal.cloud.user.auth.contract.AuthenticationType;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;

@Component
public class JpaAuthenticationRepository implements AuthenticationRepository {
    private final BaseDao<AuthenticationPo> authenticationDao;
    private final AuthenticationPoMapper authenticationPoMapper;

    @Autowired
    public JpaAuthenticationRepository(EntityManager entityManager, AuthenticationPoMapper authenticationPoMapper) {
        authenticationDao = new BaseDao<>(entityManager, AuthenticationPo.class);
        this.authenticationPoMapper = authenticationPoMapper;
    }

    @Override
    public Optional<Authentication> ofId(String id) {
        AuthenticationPo authenticationPo = authenticationDao.idEquals(id).querySingle();
        if (authenticationPo == null) {
            return Optional.empty();
        }

        return Optional.of(authenticationPoMapper.authenticationFromPo(authenticationPo));
    }

    @Override
    public String insert(Authentication authentication) {
        AuthenticationPo authenticationPo = authenticationPoMapper.authenticationToPo(authentication);
        authenticationPo.setTimeCreated(DateTime.now().toDate());

        authenticationDao.save(authenticationPo);
        return authentication.getId();
    }

    @Override
    public Authentication update(Authentication authentication) {
        authenticationDao.update(authenticationPoMapper.authenticationToPo(authentication));
        return authentication;
    }

    @Override
    public void remove(Authentication authentication) {
        authenticationDao.remove(authenticationPoMapper.authenticationToPo(authentication));
    }

    @Override
    public Long countAuthentications(IdentityType identityType, String identityId,
                                     String authenticationName, AuthenticationType authenticationType) {

        return authenticationDao.where(
                generateAuthQueries(identityType, identityId, authenticationName, authenticationType))
                .count();
    }

    @Override
    public List<Authentication> listAuthentications(Integer pageSize, Integer pageIndex,
                                                    SortType sortType, String sortBy,
                                                    IdentityType identityType, String identityId,
                                                    String authenticationName, AuthenticationType authenticationType) {
        OrderType orderType = sortType == SortType.ASC ? OrderType.ASCENDING : OrderType.DESCENDING;
        List<AuthenticationPo> poList = authenticationDao.where(generateAuthQueries(identityType, identityId,
                authenticationName, authenticationType))
                .orderBy(orderType, sortBy)
                .queryPage(pageSize, pageIndex);

        return poList.stream().map(authenticationPoMapper::authenticationFromPo).collect(Collectors.toList());
    }

    private Query[] generateAuthQueries(IdentityType identityType, String identityId,
                                        String authenticationName, AuthenticationType authenticationType) {
        return new Query[]{
                BaseDao.field("identityType").eq(identityType).when(identityType != null),
                BaseDao.field("identityId").eq(identityId).when(identityId != null),
                BaseDao.field("authenticationName").eq(authenticationName).when(authenticationName != null),
                BaseDao.field("authenticationType").eq(authenticationType).when(authenticationType != null)};
    }
}
