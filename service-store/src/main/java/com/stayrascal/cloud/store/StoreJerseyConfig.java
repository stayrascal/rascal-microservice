package com.stayrascal.cloud.store;

import com.stayrascal.cloud.common.jersey.JerseyConfig;
import com.stayrascal.cloud.common.jersey.filter.CorsFilter;
import com.stayrascal.cloud.store.resource.LocationResource;
import com.stayrascal.cloud.store.resource.StoreResource;

import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Set;

@Configuration
@ComponentScan("com.stayrascal.cloud.common.jersey")
public class StoreJerseyConfig extends JerseyConfig {
    @Autowired
    public StoreJerseyConfig(Environment env) {
        super(env);
    }

    @Override
    protected String[] getModulePackages() {
        return new String[]{
            "com.stayrascal.cloud.store.resource",
        };
    }

    @Override
    protected Set<Class<?>> getModuleJerseyClasses() {
        return Sets.newHashSet(StoreResource.class, LocationResource.class);
    }

    @Bean
    CorsFilter corsFilter() {
        return new CorsFilter();
    }
}
