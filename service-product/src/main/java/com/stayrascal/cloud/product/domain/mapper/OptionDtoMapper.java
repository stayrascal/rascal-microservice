package com.stayrascal.cloud.product.domain.mapper;

import com.stayrascal.cloud.common.mapper.DefaultMapper;
import com.stayrascal.cloud.product.contract.dto.ProductOptionDto;
import com.stayrascal.cloud.product.contract.dto.OptionPairDto;
import com.stayrascal.cloud.product.contract.dto.OptionValueDto;
import com.stayrascal.cloud.product.domain.entity.ProductOption;
import com.stayrascal.cloud.product.domain.entity.OptionPair;
import com.stayrascal.cloud.product.domain.entity.OptionValue;

import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OptionDtoMapper extends DefaultMapper {

    public OptionDtoMapper() {
        register(ProductOptionDto.class, ProductOption.class);
        register(OptionValueDto.class, OptionValue.class);
        register(OptionPairDto.class, OptionPair.class);
    }

    public ProductOption optionFromDto(ProductOptionDto productOptionDto) {
        ProductOption option = map(productOptionDto, ProductOption.class);
        option.setValues(productOptionDto.getValues().stream().map(this::optionValueFromDto).collect(Collectors.toList()));
        return option;
    }

    public OptionValue optionValueFromDto(OptionValueDto optionValueDto) {
        return map(optionValueDto, OptionValue.class);
    }

    public OptionPair optionPairFromDto(OptionPairDto optionPairDto) {
        return map(optionPairDto, OptionPair.class);
    }

    public ProductOptionDto optionToDto(ProductOption option) {
        ProductOptionDto productOptionDto = map(option, ProductOptionDto.class);
        productOptionDto.setValues(option.getValues().stream().map(this::optionValueToDto).collect(Collectors.toList()));
        return productOptionDto;
    }

    public OptionValueDto optionValueToDto(OptionValue optionValue) {
        return map(optionValue, OptionValueDto.class);
    }

    public OptionPairDto optionPairToDto(OptionPair optionPair) {
        return map(optionPair, OptionPairDto.class);
    }
}
