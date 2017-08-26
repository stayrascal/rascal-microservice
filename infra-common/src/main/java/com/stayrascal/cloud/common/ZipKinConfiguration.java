package com.stayrascal.cloud.common;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.cloud.sleuth.metric.SpanMetricReporter;
import org.springframework.cloud.sleuth.zipkin.HttpZipkinSpanReporter;
import org.springframework.cloud.sleuth.zipkin.ZipkinProperties;
import org.springframework.cloud.sleuth.zipkin.ZipkinSpanReporter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import zipkin.Span;

@Configuration
@ConditionalOnBean(value = EurekaClient.class)
public class ZipKinConfiguration {

    public static final Logger LOGGER = LoggerFactory.getLogger(ZipKinConfiguration.class);

    @Autowired
    private EurekaClient eurekaClient;

    @Autowired
    private SpanMetricReporter spanMetricReporter;

    @Autowired
    private ZipkinProperties zipkinProperties;

    @Autowired
    private RestTemplate restTemplate;

    @Value(value = "${spring.sleuth.web.skip-pattern:#{null}}")
    private String skipPattern;

    @Value(value = "${zipkin.server.name:infra-zipkin-server}")
    private String zipkinServerName;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ZipkinSpanReporter makeZipkinSpanReporter() {
        return new ZipkinSpanReporter() {

            private HttpZipkinSpanReporter delegate;
            private String baseUrl;

            @Override
            public void report(Span span) {
                InstanceInfo instance = eurekaClient.getNextServerFromEureka(zipkinServerName, false);
                if (!(baseUrl != null && instance.getHomePageUrl().equals(baseUrl))) {
                    baseUrl = instance.getHomePageUrl();
                    LOGGER.info("The base url is {}", baseUrl);
                    delegate = new HttpZipkinSpanReporter(restTemplate, baseUrl, zipkinProperties.getFlushInterval(), spanMetricReporter);
                    if (!span.name.matches(skipPattern)) {
                        delegate.report(span);
                    }
                }
            }
        };
    }
}
