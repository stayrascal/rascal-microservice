package com.stayrascal.cloud.admin.rule;

import com.stayrascal.cloud.admin.predicate.MetadataAwarePredicate;

import com.netflix.loadbalancer.AbstractServerPredicate;
import com.netflix.loadbalancer.ZoneAvoidanceRule;

public class MetadataAwareRule extends ZoneAvoidanceRule {

    @Override
    public AbstractServerPredicate getPredicate() {
        return new MetadataAwarePredicate();
    }
}
