package com.stayrascal.clould.common.contract;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;

import java.util.Map;
import java.util.function.Function;

public class QueryMap {
    private final Builder<String, String> immutableMapBuilder = ImmutableMap.builder();

    private QueryMap() {
    }

    public QueryMap put(String key, String value) {
        return this.put(key, value, (v) -> {
            return v;
        });
    }

    public <T> QueryMap put(String key, T value, Function<T, String> converter) {
        if(value != null) {
            this.immutableMapBuilder.put(key, converter.apply(value));
        }

        return this;
    }

    public Map<String, String> build() {
        return this.immutableMapBuilder.build();
    }

    public static QueryMap builder() {
        return new QueryMap();
    }
}
