package com.stayrascal.cloud.order.infrastructure.persistence;

import com.stayrascal.cloud.order.domain.entity.Transaction;
import com.stayrascal.cloud.order.domain.mapper.TransactionPoMapper;
import com.stayrascal.cloud.order.domain.repository.TransactionRepository;
import com.stayrascal.cloud.order.infrastructure.persistence.po.TransactionPo;

import com.exmertec.yaz.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import javax.persistence.EntityManager;

@Component
public class JpaTransactionRepository implements TransactionRepository {

    private final BaseDao<TransactionPo> transactionDao;
    private final TransactionPoMapper transactionPoMapper;

    @Autowired
    public JpaTransactionRepository(EntityManager entityManager, TransactionPoMapper transactionPoMapper) {
        this.transactionDao = new BaseDao<>(entityManager, TransactionPo.class);
        this.transactionPoMapper = transactionPoMapper;
    }

    @Override
    public Optional<Transaction> ofId(String id) {
        TransactionPo transactionPo = transactionDao.idEquals(id).querySingle();
        if (transactionPo == null) {
            return Optional.empty();
        }
        return Optional.of(transactionPoMapper.transactionFromPo(transactionPo));
    }

    @Override
    public String insert(Transaction transaction) {
        TransactionPo transactionPo = transactionPoMapper.transactionToPo(transaction);
        transactionDao.save(transactionPo);
        return transactionPo.getId();
    }

    @Override
    public Transaction update(Transaction transaction) {
        TransactionPo transactionPo = transactionPoMapper.transactionToPo(transaction);
        transactionDao.update(transactionPo);
        return transaction;
    }
}