package com.stayrascal.cloud.product;

import com.stayrascal.cloud.common.jersey.JerseyConfig;
import com.stayrascal.cloud.common.jersey.filter.CorsFilter;
import com.stayrascal.cloud.product.resource.CategoryResource;
import com.stayrascal.cloud.product.resource.ProductResource;
import com.stayrascal.cloud.product.resource.StoreProductResource;

import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
        return Sets.newHashSet(ProductResource.class, CategoryResource.class, StoreProductResource.class);
    }

    @Bean
    CorsFilter corsFilter() {
        return new CorsFilter();
    }
}
