package com.stayrascal.cloud.address.contract.client;

import com.stayrascal.cloud.address.contract.dto.AddressDto;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "service-address")
public interface AddressServiceClient {
    @RequestMapping(
            method = {RequestMethod.GET},
            path = {"/rest/addresses/{id}"},
            consumes = {"application/json"}
    )
    AddressDto get(@PathVariable("id") Long id);
}
