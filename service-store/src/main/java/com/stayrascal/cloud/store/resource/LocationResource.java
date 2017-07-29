package com.stayrascal.cloud.store.resource;

import com.stayrascal.cloud.common.contract.enumeration.SortType;
import com.stayrascal.cloud.common.contract.query.SortQuery;
import com.stayrascal.cloud.common.contract.result.PageResult;
import com.stayrascal.cloud.store.contract.dto.StoreDto;
import com.stayrascal.cloud.store.facade.StoreFacade;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Component
@Path("locations")
@Api(value = "store", description = "Access to store locations")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LocationResource {

    @Autowired
    private StoreFacade storeFacade;

    @GET
    @Path("/{latitude}/{longitude}/stores")
    @ApiOperation(value = "List stores by latitude and longitude")
    public PageResult list(@PathVariable("latitude") Double latitude, @PathVariable("longitude") Double longitude,
                           @RequestParam("distance") Double distance, @RequestParam("province_id") Long provinceId,
                           @RequestParam("city_id") Long cityId, @RequestParam("search_content") String searchContent,
                           @RequestParam("sort_type") SortType sortType, @RequestParam("sort_by") String sortBy,
                           @RequestParam("page_size") Integer pageSize, @RequestParam("page_index") Integer pageIndex) {
        SortQuery sortQuery = new SortQuery(sortType, sortBy, pageSize, pageIndex);
        List<StoreDto> storeDtos = storeFacade.listStoresWithLatitudeAndLongitude(sortQuery, provinceId, cityId,
                searchContent, latitude, longitude, distance);
        return new PageResult((long) storeDtos.size(), pageSize, pageIndex, storeDtos);
    }
}
