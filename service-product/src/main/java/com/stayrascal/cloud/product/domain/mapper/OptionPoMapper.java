package com.stayrascal.cloud.product.domain.mapper;

import com.stayrascal.cloud.common.mapper.DefaultMapper;
import com.stayrascal.cloud.product.domain.entity.ProductOption;
import com.stayrascal.cloud.product.domain.entity.OptionPair;
import com.stayrascal.cloud.product.domain.entity.OptionValue;
import com.stayrascal.cloud.product.infrastructure.persistence.po.OptionPairPo;
import com.stayrascal.cloud.product.infrastructure.persistence.po.ProductOptionPo;
import com.stayrascal.cloud.product.infrastructure.persistence.po.OptionValuePo;

import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OptionPoMapper extends DefaultMapper {

    public OptionPoMapper() {
        register(ProductOptionPo.class, ProductOption.class);
        register(OptionValuePo.class, OptionValue.class);
        register(OptionPairPo.class, OptionPair.class);
    }

    public ProductOption productOptionFromPo(ProductOptionPo productOptionPo) {
        ProductOption option = map(productOptionPo, ProductOption.class);
        option.setValues(productOptionPo.getValues().stream().map(this::optionValueFromPo).collect(Collectors.toList()));
        return option;
    }

    public OptionValue optionValueFromPo(OptionValuePo optionValuePo) {
        return map(optionValuePo, OptionValue.class);
    }

    public OptionPair optionPairFromPo(OptionPairPo optionPairPo) {
        return map(optionPairPo, OptionPair.class);
    }

    public ProductOptionPo productOptionToPo(ProductOption option) {
        ProductOptionPo productOptionPo = map(option, ProductOptionPo.class);
        productOptionPo.setValues(option.getValues().stream().map(this::optionValueToPo).collect(Collectors.toList()));
        return productOptionPo;
    }

    public OptionValuePo optionValueToPo(OptionValue optionValue) {
        return map(optionValue, OptionValuePo.class);
    }

    public OptionPairPo optionPairToPo(OptionPair optionPair) {
        return map(optionPair, OptionPairPo.class);
    }
}
