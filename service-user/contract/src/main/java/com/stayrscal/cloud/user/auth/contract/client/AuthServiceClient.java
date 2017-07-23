package com.stayrscal.cloud.user.auth.contract.client;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import com.stayrascal.clould.common.contract.QueryMap;
import com.stayrascal.clould.common.contract.auth.Identity;
import com.stayrascal.clould.common.contract.auth.IdentityType;
import com.stayrascal.clould.common.contract.enumeration.SortType;
import com.stayrascal.clould.common.contract.result.CreatedResult;
import com.stayrascal.clould.common.contract.result.PageResult;

import com.stayrscal.cloud.user.auth.contract.AuthenticationType;
import com.stayrscal.cloud.user.auth.contract.command.CreateAuthenticationCommand;
import com.stayrscal.cloud.user.auth.contract.command.CreateAuthenticationKeyCommand;
import com.stayrscal.cloud.user.auth.contract.command.CreateTokenCommand;
import com.stayrscal.cloud.user.auth.contract.dto.AuthenticationDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "service-user")
public interface AuthServiceClient {

    @RequestMapping(method = RequestMethod.GET, value = "/rest/authentications/{id}", produces = APPLICATION_JSON)
    AuthenticationDto getAuthentication(@PathVariable("id") String authId);

    @RequestMapping(method = RequestMethod.POST, value = "/rest/authentications", consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    CreatedResult createAuthentication(CreateAuthenticationCommand command);

    @RequestMapping(method = RequestMethod.DELETE, value = "/rest/authentications/{id}", consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    void deleteAuthentication(@PathVariable("id") String authId);

    @RequestMapping(method = RequestMethod.GET, value = "/rest/authentications", produces = APPLICATION_JSON)
    PageResult listAuthentications(
            @RequestParam("sort_type") SortType sortType,
            @RequestParam("sort_by") String sortBy,
            @RequestParam("page_size") Integer pageSize,
            @RequestParam("page_index") Integer pageIndex,
            @RequestParam Map<String, String> queryMap);

    default PageResult listAuthentications(Integer pageSize, Integer pageIndex, Map<String, String> queryMap) {
        return listAuthentications(SortType.ASC, "timeCreated", pageSize, pageIndex, queryMap);
    }

    default PageResult listAuthentications(SortType sortType, String sortBy,
                                           Integer pageSize, Integer pageIndex,
                                           IdentityType identityType, String identityId,
                                           String authenticationName, AuthenticationType authenticationType) {
        Map<String, String> queryMap = QueryMap.builder()
                .put("identity_type", identityType, Enum::name)
                .put("identity_id", identityId)
                .put("authentication_name", authenticationName)
                .put("authentication_type", authenticationType, Enum::name).build();

        return listAuthentications(sortType, sortBy, pageSize, pageIndex, queryMap);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/rest/authentications/{id}/keys",
            consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    CreatedResult createAuthenticationKey(@PathVariable("id") String authId, CreateAuthenticationKeyCommand command);

    @RequestMapping(path = "/rest/authentications/{id}/tokens", method = RequestMethod.POST,
            consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    CreatedResult createToken(@PathVariable("id") String authId, CreateTokenCommand command);

    @RequestMapping(method = RequestMethod.GET, path = "/rest/authentications/{id}/tokens/{token}",
            consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    Identity getTokenEncodedIdentity(@PathVariable("id") String authId, @PathVariable("token") String token);


}
