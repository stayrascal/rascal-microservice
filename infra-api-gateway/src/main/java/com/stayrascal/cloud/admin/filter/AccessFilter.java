package com.stayrascal.cloud.admin.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class AccessFilter extends ZuulFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccessFilter.class);

    /**
     *
     * @return
     * pre: invoked before request reached route
     * routing: invoked during request reached route
     * post:  invoked after routing and error filter
     * error: invoked if error happen during process request
     */
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        LOGGER.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
        Object accessToken = request.getParameter("accessToken");
        if (accessToken == null) {
            LOGGER.warn("access token is empty");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            return null;
        }
        LOGGER.info("access token ok");
        return null;
    }
}
