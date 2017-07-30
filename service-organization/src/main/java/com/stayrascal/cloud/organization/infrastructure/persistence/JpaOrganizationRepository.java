package com.stayrascal.cloud.organization.infrastructure.persistence;

import static com.exmertec.yaz.BaseDao.field;
import static com.google.common.collect.Lists.newArrayList;

import com.stayrascal.cloud.common.contract.enumeration.SortType;
import com.stayrascal.cloud.common.contract.query.SortQuery;
import com.stayrascal.cloud.organization.domain.entity.Organization;
import com.stayrascal.cloud.organization.domain.mapper.OrganizationPoMapper;
import com.stayrascal.cloud.organization.domain.repository.OrganizationRepository;
import com.stayrascal.cloud.organization.infrastructure.persistence.po.OrganizationPo;

import com.exmertec.yaz.BaseDao;
import com.exmertec.yaz.core.OrderType;
import com.exmertec.yaz.core.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;

@Component
public class JpaOrganizationRepository implements OrganizationRepository {
    private final BaseDao<OrganizationPo> organizationDao;
    private final OrganizationPoMapper organizationPoMapper;

    @Autowired
    public JpaOrganizationRepository(EntityManager entityManager, OrganizationPoMapper mapper) {
        this.organizationDao = new BaseDao<>(entityManager, OrganizationPo.class);
        this.organizationPoMapper = mapper;
    }

    @Override
    public Optional<Organization> ofId(String id) {
        OrganizationPo organizationPo = organizationDao.idEquals(id).querySingle();
        return organizationPo == null ? Optional.empty() : Optional.of(organizationPoMapper.organizationFromPo(organizationPo));
    }

    @Override
    public String insert(Organization organization) {
        OrganizationPo organizationPo = organizationPoMapper.organizationToPo(organization);
        organizationDao.save(organizationPo);
        return organizationPo.getId();
    }

    @Override
    public Organization update(Organization organization) {
        organizationDao.update(organizationPoMapper.organizationToPo(organization));
        return organization;
    }

    public List<Organization> listOrganizations(SortQuery sortQuery, Map<String, String> queryMap) {
        Query[] queries = generateListQueries(queryMap);
        OrderType orderType = (sortQuery.getSortType() == SortType.ASC ? OrderType.ASCENDING : OrderType.DESCENDING);
        return organizationDao.where(queries).orderBy(orderType, sortQuery.getSortBy())
                .queryPage(sortQuery.getPageSize(), sortQuery.getPageIndex())
                .stream()
                .map(organizationPoMapper::organizationFromPo)
                .collect(Collectors.toList());
    }

    private Query[] generateListQueries(Map<String, String> queryMap) {
        List<Query> queries = newArrayList();
        for (Map.Entry<String, String> entry : queryMap.entrySet()) {
            queries.add(field(entry.getKey()).eq(queryMap.get(entry.getValue())));
        }
        return queries.toArray(new Query[0]);
    }
}
