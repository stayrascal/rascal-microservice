package com.stayrascal.cloud.order.service;

import com.google.common.collect.Sets;
import com.stayrascal.cloud.common.lib.jersey.JerseyConfig;
import com.stayrascal.cloud.order.api.WeChatApi;
import com.stayrascal.cloud.order.resource.OrderResource;
import com.stayrascal.cloud.order.resource.TransactionResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Set;

@Configuration
@ComponentScan("com.stayrascal.cloud.common.jersey")
public class ServiceJerseyConfig  extends JerseyConfig{

    @Autowired
    public ServiceJerseyConfig(Environment env) {
        super(env);
    }

    @Override
    protected String[] getModulePackages() {
        return new String[]{"com.stayrascal.cloud.order.resource"};
    }

    @Override
    protected Set<Class<?>> getModuleJerseyClasses() {
        return Sets.newHashSet(OrderResource.class, TransactionResource.class, WeChatApi.class);
    }
}
