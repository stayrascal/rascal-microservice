package com.stayrascal.cloud.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import io.jmnarloch.spring.cloud.ribbon.support.RibbonFilterContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class DynamicRoutesFilter extends ZuulFilter {
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
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String tag = request.getHeader("tag");
        if(tag != null){
            RibbonFilterContextHolder.getCurrentContext().add("tag", tag);
        }
        return null;
    }
}
