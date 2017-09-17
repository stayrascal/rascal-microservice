package com.stayrascal.cloud.admin.relation;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.appinfo.InstanceInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

@Component
@Scope("prototype")
public class FeignDiscovery implements Runnable {
    protected Logger log = LoggerFactory.getLogger(FeignDiscovery.class);

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private RelateInstance relateInstance;

    @Autowired
    private RestTemplate restTemplate;

    public void setRelateInstance(RelateInstance relateInstance) {
        this.relateInstance = relateInstance;
    }

    @PostConstruct
    public void init() {
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public void run() {
        InstanceInfo instanceInfo = relateInstance.getInstanceInfo();
        String ip = instanceInfo.getIPAddr();
        String port = instanceInfo.getMetadata().get("management.port");
        if (port == null) {
            port = String.valueOf(instanceInfo.getPort());
        }
        String baseUrl = "http://" + ip + ":" + port + "/beans";
        log.info("Get Feign from {} url={}", instanceInfo.getInstanceId(), baseUrl);

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(baseUrl, String.class);
        String body = responseEntity.getBody();
        log.debug("Contexts: {}", body);

        JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructParametricType(List.class, ContextInfo.class);
        try {
            List<ContextInfo> contextInfoList = OBJECT_MAPPER.readValue(body, javaType);
            ArrayList<FeignInfo> feignInfoList = new ArrayList<>();
            for (ContextInfo contextInfo : contextInfoList) {
                List<BeanInfo> beanInfoList = contextInfo.getBeans();
                for (BeanInfo beanInfo : beanInfoList) {
                    List<String> aliases = beanInfo.getAliases();
                    String provider = null;
                    if (aliases != null && aliases.size() > 0) {
                        for (String alias : aliases) {
                            if (alias.endsWith("FeignClient")) {
                                provider = alias.substring(0, alias.length() - 11);
                                break;
                            }
                        }
                    }

                    if (provider != null) {
                        FeignInfo feignInfo = new FeignInfo();
                        feignInfo.setServiceId(provider);
                        feignInfo.setInterfaceName(beanInfo.getType());
                        feignInfoList.add(feignInfo);
                        log.info("Found feign client from {} details: {}", instanceInfo.getInstanceId(), feignInfo);
                    }
                }
            }
            relateInstance.setFeignInfoList(feignInfoList);
        } catch (IOException e) {
            log.warn("FeignDiscovery fail, instanceId={} {}", relateInstance.getInstanceInfo().getInstanceId(), e.getMessage());
        }


    }
}
