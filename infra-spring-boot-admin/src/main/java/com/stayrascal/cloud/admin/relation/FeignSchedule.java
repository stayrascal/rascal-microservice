package com.stayrascal.cloud.admin.relation;

import com.stayrascal.cloud.admin.SpringBootAdminApplication;

import com.netflix.appinfo.InstanceInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@Configurable
@EnableScheduling
public class FeignSchedule {

    protected Logger log = LoggerFactory.getLogger(FeignSchedule.class);

    private long lastRunTimestamp = 0L;

    private Map<String, Map<String, RelateInstance>> dataMap = new ConcurrentHashMap<>();

    private ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    @Autowired
    private EurekaDiscoveryClient discoveryClient;

    @Scheduled(fixedRate = 30000L)
    public void run() {
        long currentRunTimestamp = System.currentTimeMillis();

        List<RelateInstance> dirtyRelateInstanceList = loadChangeInstances();

        for (RelateInstance relateInstance : dirtyRelateInstanceList) {
            FeignDiscovery feignDiscovery = SpringBootAdminApplication.APPLICATION_CONTEXT.getBean(FeignDiscovery.class);
            feignDiscovery.setRelateInstance(relateInstance);
            log.info("Found dirty instance: {}", relateInstance.getInstanceInfo().getInstanceId());
            cachedThreadPool.submit(feignDiscovery);
        }

        lastRunTimestamp = currentRunTimestamp;
    }

    private List<RelateInstance> loadChangeInstances() {
        List<RelateInstance> dirtyRelateInstanceList = new ArrayList<>();
        List<String> services = discoveryClient.getServices();
        HashSet<String> serviceSet = new HashSet<>(services);
        Iterator<Map.Entry<String, Map<String, RelateInstance>>> iterator = dataMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Map<String, RelateInstance>> entry = iterator.next();
            if (!serviceSet.contains(entry.getKey())) {
                iterator.remove();
            }
        }

        for (String serviceId : services) {
            Map<String, RelateInstance> instanceMap = dataMap.get(serviceId);
            if (instanceMap == null) {
                instanceMap = new HashMap<>();
                dataMap.put(serviceId, instanceMap);
            }

            HashSet<String> aliveInstanceIdSet = new HashSet<>();
            List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances(serviceId);
            for (ServiceInstance serviceInstance : serviceInstanceList) {
                InstanceInfo instanceInfo = ((EurekaDiscoveryClient.EurekaServiceInstance) serviceInstance).getInstanceInfo();
                aliveInstanceIdSet.add(instanceInfo.getInstanceId());

                RelateInstance relateInstance = instanceMap.get(instanceInfo.getInstanceId());
                if (relateInstance == null) {
                    relateInstance = new RelateInstance();
                    instanceMap.put(instanceInfo.getInstanceId(), relateInstance);
                }
                relateInstance.setInstanceInfo(instanceInfo);
                if (instanceInfo.getLastDirtyTimestamp() > lastRunTimestamp - 120_000) {
                    dirtyRelateInstanceList.add(relateInstance);
                }
            }

            Iterator<Map.Entry<String, RelateInstance>> itt = instanceMap.entrySet().iterator();
            while (itt.hasNext()) {
                Map.Entry<String, RelateInstance> entry = itt.next();
                if (!aliveInstanceIdSet.contains(entry.getKey())) {
                    itt.remove();
                }
            }
        }
        return dirtyRelateInstanceList;
    }

    public Map<String, Map<String, RelateInstance>> getDataMap() {
        return dataMap;
    }
}
