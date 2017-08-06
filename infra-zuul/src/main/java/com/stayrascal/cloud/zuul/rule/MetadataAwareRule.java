package com.stayrascal.cloud.zuul.rule;

import com.stayrascal.cloud.zuul.predicate.MetadataAwarePredicate;

import com.netflix.loadbalancer.AbstractServerPredicate;
import com.netflix.loadbalancer.ZoneAvoidanceRule;

public class MetadataAwareRule extends ZoneAvoidanceRule {

    @Override
    public AbstractServerPredicate getPredicate() {
        return new MetadataAwarePredicate();
    }
}
