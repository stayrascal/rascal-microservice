package com.stayrascal.cloud.order.service;

import com.exmertec.yaz.BaseDao;
import com.stayrascal.cloud.order.infrastructure.persistence.po.PickupCodeSeedPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;

import static com.exmertec.yaz.BaseDao.field;

@Component
public class PickupCodeGeneratorService {
    private BaseDao<PickupCodeSeedPo> pickupCodeSeedDao;

    @Autowired
    public PickupCodeGeneratorService(EntityManager entityManager) {
        this.pickupCodeSeedDao = new BaseDao<>(entityManager, PickupCodeSeedPo.class);
    }

    public String generateFetchCode(String codeDifferentiator) {
        PickupCodeSeedPo pickupCodeSeedPo = pickupCodeSeedDao.where(
                field("codeDifferentiator").eq(codeDifferentiator))
                .lockBy(LockModeType.PESSIMISTIC_WRITE).querySingle();

        if (pickupCodeSeedPo == null) {
            return createSeedAndGenerateCode(codeDifferentiator);
        } else {
            return generateCode(pickupCodeSeedPo);
        }
    }

    private String generateCode(PickupCodeSeedPo pickupCodeSeedPo) {
        Integer nextCode = pickupCodeSeedPo.getNextCode();
        pickupCodeSeedPo.setNextCode(nextCode + 1);
        pickupCodeSeedDao.update(pickupCodeSeedPo);
        return String.valueOf(nextCode);
    }

    private String createSeedAndGenerateCode(String codeDifferentiator) {
        PickupCodeSeedPo newSeed = new PickupCodeSeedPo();
        newSeed.setCodeDifferentiator(codeDifferentiator);

        int initCode = 0;
        newSeed.setNextCode(initCode + 1);

        // TODO: what if a concurrent call inserted the seed first, then we will get an exception here
        pickupCodeSeedDao.save(newSeed);

        return String.valueOf(initCode);
    }
}
