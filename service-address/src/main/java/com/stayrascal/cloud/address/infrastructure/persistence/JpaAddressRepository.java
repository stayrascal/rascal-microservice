package com.stayrascal.cloud.address.infrastructure.persistence;

import com.stayrascal.cloud.address.domain.entity.Address;
import com.stayrascal.cloud.address.domain.mapper.AddressPoMapper;
import com.stayrascal.cloud.address.domain.repository.AddressRepository;
import com.stayrascal.cloud.address.infrastructure.persistence.po.AddressPo;

import com.exmertec.yaz.BaseDao;
import org.springframework.stereotype.Component;

import java.util.Optional;
import javax.persistence.EntityManager;

@Component
public class JpaAddressRepository implements AddressRepository {
    private final BaseDao<AddressPo> addressDao;
    private final AddressPoMapper addressPoMapper;

    public JpaAddressRepository(EntityManager entityManager, AddressPoMapper addressPoMapper) {
        addressDao = new BaseDao<>(entityManager, AddressPo.class);
        this.addressPoMapper = addressPoMapper;
    }

    @Override
    public Optional<Address> ofId(Long id) {
        AddressPo addressPo = addressDao.idEquals(id).querySingle();
        if (addressPo == null) {
            return Optional.empty();
        }
        return Optional.of(addressPoMapper.addressFromPo(addressPo));
    }

    @Override
    public Long insert(Address address) {
        AddressPo addressPo = addressPoMapper.addressToPo(address);
        addressDao.save(addressPo);
        return addressPo.getId();
    }

    @Override
    public Address update(Address address) {
        addressDao.update(addressPoMapper.addressToPo(address));
        return address;
    }
}
