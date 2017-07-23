package com.stayrascal.cloud.user.admin.infrastructure.persistence;

import com.stayrascal.cloud.common.constant.ErrorCode;
import com.stayrascal.cloud.common.contract.enumeration.SortType;
import com.stayrascal.cloud.common.jersey.exception.ServerErrorException;
import com.stayrascal.cloud.user.admin.domain.entity.Staff;
import com.stayrascal.cloud.user.admin.domain.repository.StaffRepository;
import com.stayrascal.cloud.user.admin.infrastructure.persistence.po.StaffPo;
import com.stayrascal.cloud.user.admin.mapper.StaffPoMapper;

import com.exmertec.yaz.BaseDao;
import com.exmertec.yaz.core.OrderType;
import com.exmertec.yaz.core.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;

@Component
public class JpaStaffRepository implements StaffRepository {
    private final StaffPoMapper staffPoMapper;
    private final BaseDao<StaffPo> staffDao;

    @Autowired
    public JpaStaffRepository(EntityManager entityManager, StaffPoMapper staffPoMapper) {
        this.staffDao = new BaseDao<>(entityManager, StaffPo.class);
        this.staffPoMapper = staffPoMapper;
    }

    public Optional<Staff> ofId(String id) {
        StaffPo staffPo = staffDao.idEquals(id).querySingle();
        return Optional.ofNullable(staffPo).map(staffPoMapper::staffFromPo);
    }

    @Override
    public String insert(Staff staff) {
        StaffPo staffPo = staffPoMapper.staffToPo(staff);
        staffDao.save(staffPo);
        return staffPo.getId();
    }

    @Override
    public Staff update(Staff staff) {
        return null;
    }

    @Override
    public Long countStaffs(String organizationId, String roleId) {
        return staffDao.where(generateListQueries(organizationId, roleId)).count();
    }

    private Query[] generateListQueries(String organizationId, String roleId) {
        if (organizationId != null || roleId != null) {
            throw new ServerErrorException(ErrorCode.INTERNAL_ERROR, "Joined query not supported yet!");
        }

        return new Query[0];
    }

    @Override
    public List<Staff> listStaffs(SortType sortType, String sortBy, Integer pageSize, Integer pageIndex,
                                  String organizationId, String roleId) {
        OrderType orderType = sortType == SortType.ASC ? OrderType.ASCENDING : OrderType.DESCENDING;

        List<StaffPo> staffPos = staffDao.where(generateListQueries(organizationId, roleId))
                .orderBy(orderType, sortBy)
                .queryPage(pageSize, pageIndex);

        return staffPoMapper.mapList(staffPos, Staff.class);
    }
}
