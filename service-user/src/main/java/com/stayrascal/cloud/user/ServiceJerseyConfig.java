package com.stayrascal.cloud.user;

import com.stayrascal.cloud.common.jersey.JerseyConfig;
import com.stayrascal.cloud.user.admin.resource.StaffResource;
import com.stayrascal.cloud.user.auth.resource.AuthenticationResource;
import com.stayrascal.cloud.user.auth.resource.RoleResource;
import com.stayrascal.cloud.user.member.resource.MemberResource;

import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Set;

@Configuration
@ComponentScan("com.stayrascal.cloud.common.jersey")
public class ServiceJerseyConfig extends JerseyConfig {

    @Autowired
    public ServiceJerseyConfig(Environment env) {
        super(env);
    }

    @Override
    protected Set<Class<?>> getModuleJerseyClasses() {
        return Sets.newHashSet(
                AuthenticationResource.class,
                MemberResource.class,
                StaffResource.class,
                RoleResource.class
        );
    }

    @Override
    protected String[] getModulePackages() {
        return new String[]{
                "com.stayrascal.cloud.user.auth.resource",
                "com.stayrascal.cloud.user.member.resource",
                "com.stayrascal.cloud.user.admin.resource",
        };
    }
}
