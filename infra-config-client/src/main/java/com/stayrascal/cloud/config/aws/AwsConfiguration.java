package com.stayrascal.cloud.config.aws;

import com.netflix.appinfo.AmazonInfo;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

//@Configuration
public class AwsConfiguration {
    @Bean
    @Profile("!default")
    public EurekaInstanceConfigBean eurekaInstanceConfigBean(InetUtils inetUtils) {
        EurekaInstanceConfigBean bean = new EurekaInstanceConfigBean(inetUtils);
        AmazonInfo eureka = AmazonInfo.Builder.newBuilder().autoBuild("eureka");
        bean.setDataCenterInfo(eureka);
        return bean;
    }
}
