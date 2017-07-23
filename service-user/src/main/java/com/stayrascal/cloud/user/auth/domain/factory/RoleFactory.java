package com.stayrascal.cloud.user.auth.domain.factory;

import com.stayrascal.cloud.common.jpa.UniqueKeyGenerator;
import com.stayrascal.cloud.user.auth.domain.entity.Role;

import com.stayrscal.cloud.user.auth.contract.command.CreateRoleCommand;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class RoleFactory {
    private final UniqueKeyGenerator keyGenerator;

    @Autowired
    public RoleFactory(UniqueKeyGenerator keyGenerator) {
        this.keyGenerator = keyGenerator;
    }

    public Role create(CreateRoleCommand command) {
        Role role = new Role();
        role.setName(command.getName());
        role.setId(keyGenerator.generateKey());
        role.setTimeCreated(DateTime.now().toDate());
        role.setRolePermissions(new ArrayList<>());
        return role;
    }
}
