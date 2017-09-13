package com.stayrascal.cloud.admin.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletRequest;

public class WebSocketHeaderFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 999;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        String upgradeHeader = request.getHeader("Upgrade");
        if (null == upgradeHeader) {
            upgradeHeader = request.getHeader("upgrade");
        }

        if (upgradeHeader != null && "websocket".equalsIgnoreCase(upgradeHeader)) {
            ctx.addZuulRequestHeader("connection", "Upgrade");
        }
        return null;
    }
}
