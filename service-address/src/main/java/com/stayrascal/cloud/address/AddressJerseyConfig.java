package com.stayrascal.cloud.address;

import com.stayrascal.cloud.address.resource.AddressResource;
import com.stayrascal.cloud.common.jersey.JerseyConfig;

import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Set;

@Configuration
@ComponentScan("com.stayrascal.cloud.common.jersey")
public class AddressJerseyConfig extends JerseyConfig {
    @Autowired
    public AddressJerseyConfig(Environment env) {
        super(env);
    }

    @Override
    protected String[] getModulePackages() {
        return new String[]{
            "com.stayrascal.cloud.address.resource",
        };
    }

    @Override
    protected Set<Class<?>> getModuleJerseyClasses() {
        return Sets.newHashSet(AddressResource.class);
    }
}
