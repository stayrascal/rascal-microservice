package com.stayrascal.cloud.bff.functional.resource.mock;

import com.stayrascal.cloud.common.contract.auth.Identity;
import com.stayrascal.cloud.common.contract.enumeration.SortType;
import com.stayrascal.cloud.common.contract.result.CreatedResult;
import com.stayrascal.cloud.common.contract.result.PageResult;
import com.stayrascal.cloud.user.auth.contract.client.AuthServiceClient;
import com.stayrascal.cloud.user.auth.contract.command.CreateAuthenticationCommand;
import com.stayrascal.cloud.user.auth.contract.command.CreateAuthenticationKeyCommand;
import com.stayrascal.cloud.user.auth.contract.command.CreateTokenCommand;
import com.stayrascal.cloud.user.auth.contract.dto.AuthenticationDto;

import feign.QueryMap;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Component
public class MockAuthServiceClient implements AuthServiceClient {
    @Override
    public AuthenticationDto getAuthentication(@PathVariable("id") String id) {
        return null;
    }

    @Override
    public CreatedResult createAuthentication(CreateAuthenticationCommand createAuthenticationCommand) {
        return null;
    }

    @Override
    public void deleteAuthentication(@PathVariable("id") String id) {

    }

    @Override
    public PageResult listAuthentications(@RequestParam("sort_type") SortType sortType,
                                          @RequestParam("sort_by") String sortBy,
                                          @RequestParam("page_size") Integer pageSize,
                                          @RequestParam("page_index") Integer pageIndex,
                                          @QueryMap Map<String, String> map) {
        return null;
    }

    @Override
    public CreatedResult createAuthenticationKey(@PathVariable("id") String id, CreateAuthenticationKeyCommand
            createAuthenticationKeyCommand) {
        return null;
    }

    @Override
    public CreatedResult createToken(@PathVariable("id") String id, CreateTokenCommand createTokenCommand) {
        return null;
    }

    @Override
    public Identity getTokenEncodedIdentity(@PathVariable("id") String id, @PathVariable("token") String token) {
        return null;
    }
}
