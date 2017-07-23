package com.stayrascal.cloud.user.member.resource;

import com.stayrascal.cloud.common.contract.enumeration.SortType;
import com.stayrascal.cloud.common.contract.query.SortQuery;
import com.stayrascal.cloud.common.contract.result.CreatedResult;
import com.stayrascal.cloud.common.contract.result.PageResult;
import com.stayrascal.cloud.user.member.facade.MemberFacade;

import com.stayrscal.cloud.user.member.contract.command.CreateMemberCommand;
import com.stayrscal.cloud.user.member.contract.command.UpdateMemberCommand;
import com.stayrscal.cloud.user.member.contract.dto.MemberDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("members")
@Api(value = "member", description = "Access to member resource")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MemberResource {
    private MemberFacade memberFacade;

    @Autowired
    public MemberResource(MemberFacade memberFacade) {
        this.memberFacade = memberFacade;
    }

    @GET
    @Path("/{id}")
    @ApiOperation(value = "Get member by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, response = MemberDto.class, message = "Get member successfully"),
            @ApiResponse(code = 404, message = "No member matches given id")
    })
    public MemberDto getMember(@NotNull @PathParam("id") String memberId) {
        return memberFacade.getMemberById(memberId);
    }

    @GET
    @ApiOperation(value = "List members")
    @ApiResponses(value = {
            @ApiResponse(code = 200, response = PageResult.class, message = "Get members successfully")
    })
    public PageResult listMembers(@ApiParam(required = true) @QueryParam("sort_type") @NotNull SortType sortType,
                                  @ApiParam(required = true) @QueryParam("sort_by") @NotNull String sortBy,
                                  @ApiParam(required = true) @QueryParam("page_size") @NotNull Integer pageSize,
                                  @ApiParam(required = true) @QueryParam("page_index") @NotNull Integer pageIndex,
                                  @QueryParam("mobile") String mobile) {
        Long totalCount = memberFacade.countMembers(mobile);
        List<MemberDto> members = memberFacade.listMembers(mobile,
                new SortQuery(sortType, sortBy, pageSize, pageIndex));

        return new PageResult(totalCount, pageSize, pageIndex, members);
    }

    @POST
    @ApiOperation(value = "Create member")
    @ApiResponses(value = {
            @ApiResponse(code = 201, response = CreatedResult.class, message = "Create member successfully")
    })
    public Response createMember(@NotNull CreateMemberCommand createMemberCommand) {
        String id = memberFacade.createMember(createMemberCommand);

        return Response.created(URI.create("/" + id)).entity(new CreatedResult(id)).build();
    }

    @PUT
    @Path("{id}")
    @ApiOperation(value = "Update member info", response = MemberDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Update member info successfully"),
            @ApiResponse(code = 404, message = "Could not find member by id")
    })
    public void updateMember(@NotNull @PathParam("id") String memberId,
                             @NotNull UpdateMemberCommand command) {
        memberFacade.updateMember(memberId, command);
    }
}
