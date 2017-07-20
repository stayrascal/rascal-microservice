package com.stayrascal.cloud.user.auth.domain.entity;

import com.google.common.collect.Lists;
import com.stayrascal.cloud.common.constant.ErrorCode;
import com.stayrascal.cloud.common.jersey.exception.BadRequestException;
import com.stayrascal.cloud.common.jersey.exception.InternalErrorException;
import com.stayrascal.cloud.common.util.HashUtil;
import org.joda.time.DateTime;

import java.util.Date;

public class PasswordAuthentication extends Authentication {
    @Override
    public AuthenticationKey createAuthenticationKey(String key, Date expiredTime) {
        if (getAuthenticationKeys() != null && !getAuthenticationKeys().isEmpty()) {
            throw new BadRequestException(ErrorCode.INTERNAL_ERROR,
                    "Already have authentication key set on {}, you can only update it!", getId());
        }

        AuthenticationKey authenticationKey = new AuthenticationKey(HashUtil.digestHex(key), expiredTime);
        setAuthenticationKeys(Lists.newArrayList(authenticationKey));

        return authenticationKey;
    }

    @Override
    public void authenticate(String key) {
        if (getAuthenticationKeys() == null || getAuthenticationKeys().isEmpty()) {
            throw new InternalErrorException(ErrorCode.INTERNAL_ERROR,
                    "No authentication key for password, of auth id {}", getId());
        }

        if (getAuthenticationKeys().size() > 1) {
            throw new InternalErrorException(ErrorCode.INTERNAL_ERROR,
                    "More than 1 key for password authentication, of auth id {}", getId());
        }

        AuthenticationKey authenticationKey = getAuthenticationKeys().get(0);

        if (!authenticationKey.getKeyValue().equals(HashUtil.digestHex(key))) {
            throw new BadRequestException(ErrorCode.INTERNAL_ERROR, "Password not match, of auth id {}", getId());
        }

        if (authenticationKey.getExpiredTime().before(DateTime.now().toDate())) {
            throw new BadRequestException(ErrorCode.INTERNAL_ERROR, "Password expired, need to update {}", getId());
        }

    }
}
