package com.stayrascal.cloud.common.lib.jersey;

import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

public class JerseyHelpers {
    public JerseyHelpers() {
    }

    public static URI uriOfCreated(UriInfo uriInfo, String createdResourceId) {
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(createdResourceId);
        return builder.build(new Object[0]);
    }
}
