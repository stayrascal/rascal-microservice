package com.stayrascal.cloud.common.ddd;

import java.util.Optional;

public interface Repository<T, IDT> {
    Optional<T> ofId(IDT id);

    IDT insert(T obj);

    T update(T obj);
}
