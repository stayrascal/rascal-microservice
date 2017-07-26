package com.stayrascal.cloud.bff.auth;

import com.stayrascal.cloud.bff.entity.SignInRequest;
import com.stayrascal.cloud.common.constant.ErrorCode;
import com.stayrascal.cloud.common.contract.auth.Authorization;
import com.stayrascal.cloud.common.contract.auth.IdentityType;
import com.stayrascal.cloud.common.contract.result.PageResult;
import com.stayrascal.cloud.common.jersey.exception.BadRequestException;
import com.stayrascal.cloud.common.jersey.exception.ServerErrorException;
import com.stayrascal.cloud.organization.contract.client.OrganizationServiceClient;
import com.stayrascal.cloud.organization.contract.client.StoreServiceClient;
import com.stayrascal.cloud.organization.contract.dto.OrganizationDto;
import com.stayrascal.cloud.organization.contract.dto.StoreDto;
import com.stayrascal.cloud.organization.contract.enumeration.OrganizationType;

import com.google.common.collect.ImmutableMap;
import com.stayrscal.cloud.user.admin.contract.client.StaffServiceClient;
import com.stayrscal.cloud.user.admin.contract.dto.StaffDto;
import com.stayrscal.cloud.user.auth.contract.AuthenticationType;
import com.stayrscal.cloud.user.auth.contract.client.AuthServiceClient;
import com.stayrscal.cloud.user.auth.contract.client.RoleServiceClient;
import com.stayrscal.cloud.user.auth.contract.command.CreateTokenCommand;
import com.stayrscal.cloud.user.auth.contract.dto.AuthenticationDto;
import com.stayrscal.cloud.user.auth.contract.dto.RoleDto;
import io.swagger.annotations.Api;
import mjson.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("sign-in")
@Api(value = "authentication", description = "Access to attachment resource")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthenticationApi {

    @Autowired
    private AuthServiceClient authServiceClient;

    @Autowired
    private StaffServiceClient staffServiceClient;

    @Autowired
    private RoleServiceClient roleServiceClient;

    @Autowired
    private StoreServiceClient storeServiceClient;

    @Autowired
    private OrganizationServiceClient organizationServiceClient;

    @POST
    public Response signIn(SignInRequest command) {
        PageResult pageResult = authServiceClient.listAuthentications(1, 0,
                ImmutableMap.of("authentication_name", command.getAccountId(), "authentication_type", AuthenticationType.PASSWORD.name()));
        if (pageResult.getItems() == null || pageResult.getItems().isEmpty()) {
            throw new BadRequestException(ErrorCode.INTERNAL_ERROR, "Account not existed: {}", command.getAccountId());
        }

        AuthenticationDto authentication = (AuthenticationDto) pageResult.getItems().get(0);
        if (authentication.getIdentityType() != IdentityType.STAFF) {
            throw new BadRequestException(ErrorCode.INTERNAL_ERROR, "Account id {} is not a staff!", command.getAccountId());
        }

        String token = authServiceClient.createToken(authentication.getId(), new CreateTokenCommand(command.getPassword())).getId();

        StaffDto staff = staffServiceClient.getStaff(authentication.getIdentityId());
        final Json result = generateResult(token, staff);

        return Response.ok().entity(result.toString()).build();
    }

    private Json generateResult(String token, StaffDto staff) {
        final Json result = Json.object();
        result.set("token", token);
        result.set("staff", generateStaff(staff));
        return result;
    }

    private Json generateStaff(StaffDto staff) {
        final Json staffJson = Json.object();
        staffJson.set("name", staff.getName());
        staffJson.set("authorization", getAuthorizations(staff));
        return staffJson;
    }

    private Json getAuthorizations(StaffDto staff) {
        final Json authorizations = Json.array();
        for (Authorization authorization : staff.getAuthorizations()) {
            Json auth = Json.object();
            Json role = getRole(authorization.getRoleId());
            Json organization = getOrganization(authorization.getOrganizationId());

            auth.set("role", role);
            auth.set("organization", organization);

            authorizations.add(auth);
        }
        return authorizations;
    }

    private Json getOrganization(String organizationId) {
        if (organizationId == null) {
            return null;
        }
        OrganizationDto organizationDto = organizationServiceClient.getOrganization(organizationId);
        final Json organization = Json.object();
        organization.set("id", organizationDto.getId());
        organization.set("type", organizationDto.getType().name());

        if (organizationDto.getType() == OrganizationType.STORE) {
            PageResult pageResult = storeServiceClient.listStores(1, 0, ImmutableMap.of("organization_id", organizationId));
            if (pageResult.getItems().isEmpty()) {
                throw new ServerErrorException(ErrorCode.INTERNAL_ERROR, "No store found within organization if {}", organizationId);
            }
            StoreDto storeDto = (StoreDto) pageResult.getItems().get(0);
            organization.set("storeId", storeDto.getId());
            organization.set("storeName", storeDto.getName());
        }
        return organization;
    }

    private Json getRole(String roleId) {
        RoleDto roleDto = roleServiceClient.getRole(roleId);
        Json role = Json.object();
        role.set("name", roleDto.getName());
        Json permissions = Json.array();
        for (RoleDto.RolePermissionDto permissionDto : roleDto.getPermissions()) {
            Json permission = Json.object();
            permission.set("permissionType", permissionDto.getPermissionType());
            permissions.add(permission);
        }
        role.set("permissions", permissions);
        return role;
    }
}
