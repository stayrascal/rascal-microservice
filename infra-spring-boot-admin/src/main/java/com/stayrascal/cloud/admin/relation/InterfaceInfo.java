package com.stayrascal.cloud.admin.relation;

import java.util.HashSet;
import java.util.Set;

public class InterfaceInfo {
    private String name;
    private Set<String> consumes;
    private Set<String> providers;

    public InterfaceInfo(String name) {
        this.name = name;
        consumes = new HashSet<>();
        providers = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getConsumes() {
        return consumes;
    }

    public void setConsumes(Set<String> consumes) {
        this.consumes = consumes;
    }

    public Set<String> getProviders() {
        return providers;
    }

    public void setProviders(Set<String> providers) {
        this.providers = providers;
    }
}
