package com.stayrascal.cloud.mapping.contract.client;

import com.stayrascal.cloud.common.contract.result.CreatedResult;
import com.stayrascal.cloud.common.contract.result.PageResult;
import com.stayrascal.cloud.mapping.contract.command.CreateStaffAddressMappingCommand;
import com.stayrascal.cloud.mapping.contract.command.CreateStaffOrderMappingCommand;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.ws.rs.core.MediaType;

@FeignClient(name = "service-mapping")
public interface MappingServiceClient {

    @RequestMapping(method = RequestMethod.POST, value = "/rest/mappings", consumes = MediaType.APPLICATION_JSON)
    CreatedResult addAddressMapping(CreateStaffAddressMappingCommand command);

    @RequestMapping(method = RequestMethod.POST, value = "/rest/mappings", consumes = MediaType.APPLICATION_JSON)
    CreatedResult addOrderMapping(CreateStaffOrderMappingCommand command);

    @RequestMapping(method = RequestMethod.GET, value = "/rest/mappings/addressId/{userId}", consumes = MediaType.APPLICATION_JSON)
    PageResult retrieveAddressIds(@PathVariable("userId") String userId);

    @RequestMapping(method = RequestMethod.GET, value = "/rest/mappings/orderId/{userId}", consumes = MediaType.APPLICATION_JSON)
    PageResult retrieveOrderIds(@PathVariable("userId") String userId);
}
