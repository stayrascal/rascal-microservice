package com.stayrascal.cloud.bff.configuration;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.cloud.sleuth.metric.SpanMetricReporter;
import org.springframework.cloud.sleuth.zipkin.HttpZipkinSpanReporter;
import org.springframework.cloud.sleuth.zipkin.ZipkinSpanReporter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import zipkin.Span;

@Configuration
@ConditionalOnBean(value = EurekaClient.class)
public class ZipKinConfiguration {

    @Autowired
    private EurekaClient eurekaClient;

    @Autowired
    private SpanMetricReporter spanMetricReporter;

    @Autowired
    private org.springframework.cloud.sleuth.zipkin.ZipkinProperties zipkinProperties;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${spring.sleuth.web.skip-pattern}")
    private String skipPattern;

    @Bean
    public ZipkinSpanReporter makeZipkinSpanReporter() {
        return new ZipkinSpanReporter() {

            private HttpZipkinSpanReporter delegate;
            private String baseUrl;

            @Override
            public void report(Span span) {
                InstanceInfo instance = eurekaClient.getNextServerFromEureka("infra-zipkin-server", false);
                if (!(baseUrl != null && instance.getHomePageUrl().equals(baseUrl))) {
                    baseUrl = instance.getHomePageUrl();
                    delegate = new HttpZipkinSpanReporter(restTemplate, baseUrl, zipkinProperties.getFlushInterval(), spanMetricReporter);
                    if (!span.name.matches(skipPattern)) {
                        delegate.report(span);
                    }
                }
            }
        };
    }
}
