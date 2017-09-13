package com.stayrascal.cloud.admin.predicate;

import com.netflix.loadbalancer.AbstractServerPredicate;
import com.netflix.loadbalancer.PredicateKey;
import com.netflix.niws.loadbalancer.DiscoveryEnabledServer;
import io.jmnarloch.spring.cloud.ribbon.api.RibbonFilterContext;
import io.jmnarloch.spring.cloud.ribbon.support.RibbonFilterContextHolder;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class MetadataAwarePredicate extends AbstractServerPredicate {
    @Override
    public boolean apply(PredicateKey input) {
        if(input == null || !(input.getServer() instanceof DiscoveryEnabledServer)){
            return true;
        }
        DiscoveryEnabledServer server = (DiscoveryEnabledServer) input.getServer();
        final RibbonFilterContext context = RibbonFilterContextHolder.getCurrentContext();
        final Set<Map.Entry<String, String>> attributes = Collections.unmodifiableSet(context.getAttributes().entrySet());
        final Map<String, String> metadata = server.getInstanceInfo().getMetadata();
        return metadata.entrySet().containsAll(attributes);
    }
}
