package com.stayrascal.cloud.auth.contract.client;

import com.stayrascal.cloud.auth.contract.command.CreateAuthenticationCommand;
import com.stayrascal.cloud.auth.contract.command.CreateAuthenticationKeyCommand;
import com.stayrascal.cloud.auth.contract.command.CreateTokenCommand;
import com.stayrascal.cloud.auth.contract.dto.IdentityDto;
import com.stayrascal.cloud.auth.contract.enumeration.AccountGroup;
import com.stayrascal.cloud.auth.contract.enumeration.AccountRole;
import com.stayrascal.cloud.auth.contract.enumeration.AuthenticationType;
import com.stayrascal.cloud.common.contract.result.CreatedResult;
import com.stayrascal.cloud.common.contract.result.PageResult;
import com.stayrascal.cloud.common.feign.FeignConfiguration;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "korprulu-auth",
        configuration = {FeignConfiguration.class}
)
public interface AuthServiceClient {
    @RequestMapping(
            method = {RequestMethod.POST},
            value = {"/rest/authentications"},
            consumes = {"application/json"},
            produces = {"application/json"}
    )
    CreatedResult createAuthentication(CreateAuthenticationCommand var1);

    @RequestMapping(
            method = {RequestMethod.DELETE},
            value = {"/rest/authentications/{id}"},
            consumes = {"application/json"},
            produces = {"application/json"}
    )
    void deleteAuthentication(@PathVariable("id") String var1);

    @RequestMapping(
            method = {RequestMethod.GET},
            value = {"/rest/authentications"},
            produces = {"application/json"}
    )
    PageResult listAuthentications(@RequestParam("page_size") Integer var1, @RequestParam("page_index") Integer var2, @RequestParam("account_id") String var3, @RequestParam("account_role") AccountRole var4, @RequestParam("account_group") AccountGroup var5, @RequestParam("authentication_name") String var6, @RequestParam("authentication_type") AuthenticationType var7);

    @RequestMapping(
            method = {RequestMethod.POST},
            value = {"/rest/authentications/{id}/keys"},
            consumes = {"application/json"},
            produces = {"application/json"}
    )
    CreatedResult createAuthenticationKey(@PathVariable("id") String var1, CreateAuthenticationKeyCommand var2);

    @RequestMapping(
            path = {"/rest/authentications/{id}/tokens"},
            method = {RequestMethod.POST},
            consumes = {"application/json"},
            produces = {"application/json"}
    )
    CreatedResult createToken(@PathVariable("id") String var1, CreateTokenCommand var2);

    @RequestMapping(
            method = {RequestMethod.GET},
            path = {"/rest/authentications/{id}/tokens/{token}"},
            consumes = {"application/json"},
            produces = {"application/json"}
    )
    IdentityDto getTokenEncodedIdentity(@PathVariable("id") String var1, @PathVariable("token") String var2);
}

