package com.stayrascal.cloud.product.infrastructure.persistence;

import static com.exmertec.yaz.BaseDao.field;

import com.stayrascal.cloud.common.contract.enumeration.SortType;
import com.stayrascal.cloud.common.contract.query.SortQuery;
import com.stayrascal.cloud.product.domain.entity.Product;
import com.stayrascal.cloud.product.domain.mapper.ProductPoMapper;
import com.stayrascal.cloud.product.domain.repository.ProductRepository;
import com.stayrascal.cloud.product.infrastructure.persistence.po.ProductPo;

import com.exmertec.yaz.BaseDao;
import com.exmertec.yaz.core.OrderType;
import com.exmertec.yaz.core.Query;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;

@Component
public class JpaProductRepository implements ProductRepository {

    private final BaseDao<ProductPo> productDao;
    private final ProductPoMapper mapper;

    @Autowired
    public JpaProductRepository(EntityManager entityManager, ProductPoMapper mapper) {
        this.productDao = new BaseDao<>(entityManager, ProductPo.class);
        this.mapper = mapper;
    }

    @Override
    public Optional<Product> ofId(String id) {
        ProductPo productPo = productDao.idEquals(id).querySingle();
        if (productPo == null) {
            return Optional.empty();
        }
        return Optional.of(mapper.productFromPo(productPo));
    }

    @Override
    public String insert(Product product) {
        ProductPo productPo = mapper.productToPo(product);
        productDao.save(productPo);
        return productPo.getId();
    }

    @Override
    public Product update(Product product) {
        productDao.update(mapper.productToPo(product));
        return product;
    }

    @Override
    public void delete(Product product) {
        productDao.remove(mapper.productToPo(product));
    }

    public List<Product> listProducts(SortQuery sortQuery, Map<String, String> queryMaps) {
        Query[] queries = generateListQueries(queryMaps);
        OrderType orderType = (sortQuery.getSortType() == SortType.ASC ? OrderType.ASCENDING : OrderType.DESCENDING);
        return productDao.where(queries).orderBy(orderType, sortQuery.getSortBy())
                .queryPage(sortQuery.getPageSize(), sortQuery.getPageIndex())
                .stream()
                .map(mapper::productFromPo)
                .collect(Collectors.toList());
    }

    private Query[] generateListQueries(Map<String, String> queryMaps) {
        List<Query> queries = Lists.newArrayList();
        for (Map.Entry<String, String> entry : queryMaps.entrySet()) {
            queries.add(field(entry.getKey()).eq(entry.getValue()));
        }
        return queries.toArray(new Query[0]);
    }
}
