package com.stayrascal.cloud.auth;

import com.stayrascal.cloud.auth.resource.AuthResource;
import com.stayrascal.cloud.common.jersey.JerseyConfig;

import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Set;

@Configuration
@ComponentScan("com.stayrascal.cloud.common.jersey")
public class AuthJerseyConfig extends JerseyConfig {
    @Autowired
    public AuthJerseyConfig(Environment env) {
        super(env);
    }

    @Override
    protected String[] getModulePackages() {
        return new String[]{
                "com.stayrascal.cloud.auth.resource",
        };
    }

    @Override
    protected Set<Class<?>> getModuleJerseyClasses() {
        return Sets.newHashSet(AuthResource.class);
    }
}
