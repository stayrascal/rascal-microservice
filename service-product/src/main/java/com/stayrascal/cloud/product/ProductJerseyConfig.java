package com.stayrascal.cloud.product;

import com.stayrascal.cloud.common.jersey.JerseyConfig;
import com.stayrascal.cloud.product.resource.ProductResource;

import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Set;

@Configuration
@ComponentScan("com.stayrascal.cloud.common.jersey")
public class ProductJerseyConfig extends JerseyConfig {
    @Autowired
    public ProductJerseyConfig(Environment env) {
        super(env);
    }

    @Override
    protected String[] getModulePackages() {
        return new String[]{
            "com.stayrascal.cloud.product.resource",
        };
    }

    @Override
    protected Set<Class<?>> getModuleJerseyClasses() {
        return Sets.newHashSet(ProductResource.class);
    }
}
