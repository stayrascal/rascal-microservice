package com.stayrascal.cloud.common.jersey;

import com.stayrascal.cloud.common.jersey.exception.handler.BaseExceptionMapper;
import com.stayrascal.cloud.common.jersey.exception.handler.FeignExceptionMapper;
import com.stayrascal.cloud.common.jersey.exception.handler.HystrixExceptionMapper;
import com.stayrascal.cloud.common.jersey.exception.handler.RemoteCallExceptionMapper;
import com.stayrascal.cloud.common.jersey.exception.handler.UnhandledExceptionMapper;
import com.stayrascal.cloud.common.jersey.exception.handler.WebApplicationExceptionMapper;
import com.stayrascal.cloud.common.jersey.filter.CorsFilter;
import com.stayrascal.cloud.common.jersey.filter.IdentityFilter;

import com.google.common.base.Joiner;
import com.google.common.collect.Sets;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.core.env.Environment;

import java.util.Set;

public abstract class JerseyConfig extends ResourceConfig {
    private static final Class<?>[] COMMON_JERSEY_CLASSES = new Class[]{ApiListingResource.class, SwaggerSerializers.class, RemoteCallExceptionMapper.class, BaseExceptionMapper.class, UnhandledExceptionMapper.class, FeignExceptionMapper.class, HystrixExceptionMapper.class, WebApplicationExceptionMapper.class, IdentityFilter.class, CorsFilter.class};

    public JerseyConfig(Environment env) {
        Set<Class<?>> classes = Sets.newHashSet(COMMON_JERSEY_CLASSES);
        classes.addAll(this.getModuleJerseyClasses());
        this.registerClasses(classes);
        this.initSwaggerBeanConfig(env);
    }

    private void initSwaggerBeanConfig(Environment env) {
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion(env.getProperty("cloud.jersey.swagger.version", "1.0.0"));
        beanConfig.setSchemes(new String[]{env.getProperty("cloud.jersey.swagger.scheme", "http")});
        beanConfig.setHost(env.getProperty("cloud.jersey.swagger.host", "localhost:8080"));
        beanConfig.setBasePath(env.getProperty("cloud.jersey.swagger.base-path", "/"));
        beanConfig.setResourcePackage(Joiner.on(",").join(this.getModulePackages()));
        beanConfig.setScan(true);
    }

    protected Set<Class<?>> getModuleJerseyClasses() {
        return Sets.newHashSet();
    }

    protected abstract String[] getModulePackages();
}
