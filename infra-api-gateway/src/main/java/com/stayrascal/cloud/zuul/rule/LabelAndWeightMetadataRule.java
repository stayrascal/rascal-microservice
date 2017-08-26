package com.stayrascal.cloud.zuul.rule;

import com.stayrascal.cloud.zuul.interceptor.CoreHeaderInterceptor;

import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ZoneAvoidanceRule;
import com.netflix.niws.loadbalancer.DiscoveryEnabledServer;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class LabelAndWeightMetadataRule extends ZoneAvoidanceRule {
    private static final String META_DATA_KEY_LABEL_AND = "labelAnd";
    private static final String META_DATA_KEY_LABEL_OR = "labelOr";
    private static final String META_DATA_KEY_WEIGHT = "weight";

    private Random random = new Random();

    @Override
    public Server choose(Object key) {
        List<Server> servers = this.getPredicate().getEligibleServers(this.getLoadBalancer().getAllServers(), key);
        if (CollectionUtils.isEmpty(servers)) {
            return null;
        }

        int totalWeight = 0;
        Map<Server, Integer> serverWeightMap = new HashMap<>();
        for (Server server : servers) {
            Map<String, String> metadata = ((DiscoveryEnabledServer) server).getInstanceInfo().getMetadata();

            String labelOr = metadata.get(META_DATA_KEY_LABEL_OR);
            if (isAnyLabelExist(labelOr)){
                return server;
            }

            String labelAnd = metadata.get(META_DATA_KEY_LABEL_AND);
            if (isAllLabelsExist(labelAnd)){
                return server;
            }

            String strWeight = metadata.get(META_DATA_KEY_WEIGHT);
            int weight = 100;
            try {
                weight = Integer.parseInt(strWeight);
            } catch (Exception e) {
            }

            if (weight <= 0) {
                continue;
            }
            serverWeightMap.put(server, weight);
            totalWeight += weight;
        }

        int randomWeight = this.random.nextInt(totalWeight);
        int current = 0;
        for (Map.Entry<Server, Integer> entry : serverWeightMap.entrySet()) {
            current += entry.getValue();
            if (randomWeight <= current) {
                return entry.getKey();
            }
        }
        return null;
    }

    private boolean isAllLabelsExist(String labelAnd) {
        if (!StringUtils.isEmpty(labelAnd)) {
            List<String> metadataLabel = Arrays.asList(labelAnd.split(CoreHeaderInterceptor.HEADER_LABEL_SPLIT));
            if (CoreHeaderInterceptor.label.get().containsAll(metadataLabel)) {
                return true;
            }
        }
        return false;
    }

    private boolean isAnyLabelExist(String labelOr) {
        if (!StringUtils.isEmpty(labelOr)) {
            List<String> metadataLabel = Arrays.asList(labelOr.split(CoreHeaderInterceptor.HEADER_LABEL_SPLIT));
            for (String label : metadataLabel) {
                if (CoreHeaderInterceptor.label.get().contains(label)) {
                    return true;
                }
            }
        }
        return false;
    }

}
