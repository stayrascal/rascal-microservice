package com.stayrascal.cloud.common.lib.mapper;

@FunctionalInterface
public interface MapperFunction<F, T> {
    T apply(F var1, T var2);
}
