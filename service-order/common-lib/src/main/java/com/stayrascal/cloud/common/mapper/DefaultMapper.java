package com.stayrascal.cloud.common.mapper;

import ma.glasnost.orika.DefaultFieldMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.converter.builtin.PassThroughConverter;
import org.joda.time.DateTime;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class DefaultMapper {
    protected final MapperFactory mapperFactory = (new ma.glasnost.orika.impl.DefaultMapperFactory.Builder()).build();

    protected DefaultMapper() {
        ConverterFactory converterFactory = this.mapperFactory.getConverterFactory();
        converterFactory.registerConverter(new PassThroughConverter(new Type[]{DateTime.class}));
        converterFactory.registerConverter(new BidirectionalConverter<Date, DateTime>() {
            @Override
            public DateTime convertTo(Date source, ma.glasnost.orika.metadata.Type<DateTime> destinationType, MappingContext mappingContext) {
                return new DateTime(source);
            }

            @Override
            public Date convertFrom(DateTime source, ma.glasnost.orika.metadata.Type<Date> destinationType, MappingContext mappingContext) {
                return source.toDate();
            }
        });
    }

    protected void register(Class<?> typeA, Class<?> typeB) {
        this.mapperFactory.classMap(typeA, typeB).byDefault(new DefaultFieldMapper[0]).register();
    }

    public <T> T map(Object obj, Class<T> targetType) {
        return this.mapperFactory.getMapperFacade().map(obj, targetType);
    }

    public <F, T> T map(F obj, Class<T> targetType, MapperFunction<F, T> mapperFunction) {
        return obj == null ? null : mapperFunction.apply(obj, this.mapperFactory.getMapperFacade().map(obj, targetType));
    }

    public <T> List<T> mapList(List<? extends Object> sources, Class<T> targetType) {
        return sources == null ? null : (List) sources.stream().map((o) -> {
            return this.mapperFactory.getMapperFacade().map(o, targetType);
        }).collect(Collectors.toList());
    }

    public <F, T> List<T> mapList(List<F> sources, Class<T> targetType, MapperFunction<F, T> mapperFunction) {
        return sources == null ? null : (List) sources.stream().map((o) -> {
            return mapperFunction.apply(o, this.mapperFactory.getMapperFacade().map(o, targetType));
        }).collect(Collectors.toList());
    }

    public static final DefaultMapper.Builder builder() {
        return new DefaultMapper.Builder();
    }

    public static final class Builder {
        private final DefaultMapper mapper;

        private Builder() {
            this.mapper = new DefaultMapper();
        }

        public DefaultMapper.Builder register(Class<?> typeA, Class<?> typeB) {
            this.mapper.register(typeA, typeB);
            return this;
        }

        public DefaultMapper.Builder customize(Consumer<MapperFactory> consumer) {
            consumer.accept(this.mapper.mapperFactory);
            return this;
        }

        public DefaultMapper build() {
            return this.mapper;
        }
    }
}
