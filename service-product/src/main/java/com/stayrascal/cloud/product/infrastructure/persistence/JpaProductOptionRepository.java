package com.stayrascal.cloud.product.infrastructure.persistence;

import com.stayrascal.cloud.product.domain.entity.ProductOption;
import com.stayrascal.cloud.product.domain.mapper.OptionPoMapper;
import com.stayrascal.cloud.product.domain.repository.ProductOptionRepository;
import com.stayrascal.cloud.product.infrastructure.persistence.po.ProductOptionPo;

import com.exmertec.yaz.BaseDao;
import org.springframework.stereotype.Component;

import java.util.Optional;
import javax.persistence.EntityManager;

@Component
public class JpaProductOptionRepository implements ProductOptionRepository {

    private final BaseDao<ProductOptionPo> productOptionPoDao;
    private final OptionPoMapper mapper;

    public JpaProductOptionRepository(EntityManager entityManager, OptionPoMapper mapper) {
        this.productOptionPoDao = new BaseDao<>(entityManager, ProductOptionPo.class);
        this.mapper = mapper;
    }

    @Override
    public Optional<ProductOption> ofId(String id) {
        ProductOptionPo productOptionPo = productOptionPoDao.idEquals(id).querySingle();
        return productOptionPo == null ? Optional.empty() : Optional.of(mapper.productOptionFromPo(productOptionPo));
    }

    @Override
    public String insert(ProductOption productOption) {
        ProductOptionPo productOptionPo = mapper.productOptionToPo(productOption);
        productOptionPoDao.save(productOptionPo);
        return productOptionPo.getId();
    }

    @Override
    public ProductOption update(ProductOption productOption) {
        productOptionPoDao.update(mapper.productOptionToPo(productOption));
        return productOption;
    }
}
