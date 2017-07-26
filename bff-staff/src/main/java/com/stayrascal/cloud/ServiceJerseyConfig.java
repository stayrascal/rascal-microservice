package com.stayrascal.cloud;

import com.stayrascal.cloud.bff.auth.AuthenticationApi;
import com.stayrascal.cloud.bff.order.OrderApi;
import com.stayrascal.cloud.bff.product.CategoryApi;
import com.stayrascal.cloud.bff.product.ProductApi;
import com.stayrascal.cloud.bff.product.StoreProductApi;
import com.stayrascal.cloud.bff.staff.StaffApi;
import com.stayrascal.cloud.bff.store.StoreApi;
import com.stayrascal.cloud.common.jersey.JerseyConfig;

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
        return Sets.newHashSet(StoreApi.class, AuthenticationApi.class, ProductApi.class,
                CategoryApi.class, OrderApi.class, StoreProductApi.class, StaffApi.class);
    }

    @Override
    protected String[] getModulePackages() {
        return new String[]{
                "com.stayrascal.cloud.bff"
        };
    }
}
