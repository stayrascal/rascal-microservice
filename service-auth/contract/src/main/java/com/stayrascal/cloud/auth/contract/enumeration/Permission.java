package com.stayrascal.cloud.auth.contract.enumeration;

import java.util.Arrays;
import java.util.List;

public enum Permission {
    ADMIN,
    MANAGER,
    CLERK,
    MEMBER;

    private Permission() {
    }

    public static List<Permission> getPermissions(AccountRole role) {
        return Arrays.asList(new Permission[]{valueOf(role.name())});
    }
}
