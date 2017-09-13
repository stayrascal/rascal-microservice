package com.stayrascal.cloud.admin.configuration;

import com.stayrascal.cloud.admin.interceptor.CoreHeaderInterceptor;
import com.stayrascal.cloud.admin.interceptor.CoreHttpRequestInterceptor;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//@Configuration
//@EnableWebMvc
//@RibbonClients(defaultConfiguration = DefaultRibbonConfiguration.class)
public class CoreAutoConfiguration extends WebMvcConfigurerAdapter {

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate(){
        RestTemplate template = new RestTemplate();
        template.getInterceptors().add(new CoreHttpRequestInterceptor());
        return template;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CoreHeaderInterceptor());
    }
}
