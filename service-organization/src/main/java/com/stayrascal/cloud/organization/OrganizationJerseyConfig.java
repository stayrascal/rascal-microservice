package com.stayrascal.cloud.organization;

import com.stayrascal.cloud.common.jersey.JerseyConfig;
import com.stayrascal.cloud.organization.resource.OrganizationResource;

import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Set;

@Configuration
@ComponentScan("com.stayrascal.cloud.common.jersey")
public class OrganizationJerseyConfig extends JerseyConfig {
    @Autowired
    public OrganizationJerseyConfig(Environment env) {
        super(env);
    }

    @Override
    protected String[] getModulePackages() {
        return new String[]{
            "com.stayrascal.cloud.organization.resource",
        };
    }

    @Override
    protected Set<Class<?>> getModuleJerseyClasses() {
        return Sets.newHashSet(OrganizationResource.class);
    }
}
