package com.stayrascal.cloud.common.jersey.filter;

import com.stayrascal.cloud.common.identity.IdentityHeaderConverter;

import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class IdentityFilter implements ContainerRequestFilter {
    public IdentityFilter() {
    }

    public void filter(ContainerRequestContext requestContext) throws IOException {
        requestContext.setProperty("identity", IdentityHeaderConverter.headerToIdentity(requestContext.getHeaders()));
    }
}