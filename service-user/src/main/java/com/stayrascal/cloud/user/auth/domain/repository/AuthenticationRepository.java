package com.stayrascal.cloud.user.auth.domain.repository;

import com.stayrascal.cloud.common.ddd.Repository;
import com.stayrascal.cloud.user.auth.domain.entity.Authentication;
import com.stayrascal.cloud.common.contract.auth.IdentityType;
import com.stayrascal.cloud.common.contract.enumeration.SortType;

import com.stayrscal.cloud.user.auth.contract.AuthenticationType;

import java.util.List;

public interface AuthenticationRepository extends Repository<Authentication, String> {
    void remove(Authentication authentication);

    Long countAuthentications(IdentityType identityType, String identityId,
                              String authenticationName, AuthenticationType authenticationType);

    List<Authentication> listAuthentications(Integer pageSize, Integer pageIndex,
                                             SortType sortType, String sortBy,
                                             IdentityType identityType, String identityId,
                                             String authenticationName,
                                             AuthenticationType authenticationType);
}
