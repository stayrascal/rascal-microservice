package com.stayrascal.cloud.product.domain.entity;

import java.util.Date;
import java.util.List;

public class ProductOption {
    private String id;
    private String name;
    private List<OptionValue> values;
    private Date timeCreated;

    public Date getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<OptionValue> getValues() {
        return values;
    }

    public void setValues(List<OptionValue> values) {
        this.values = values;
    }
}
