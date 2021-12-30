package com.werfen.laboratory.core.tenant;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RequestTenantInterceptor implements HandlerInterceptor {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        if (request.getRequestURI().contains("/api/v1")) {
            logger.info("In preHandle we are Intercepting the Request");
            logger.info("____________________________________________");
            String requestURI = request.getRequestURI();
            String tenantID = request.getHeader("X-TenantID");
            logger.info("RequestURI::" + requestURI + " || Search for X-TenantID  :: " + tenantID);
            logger.info("____________________________________________");
            if (tenantID == null) {
                response.getWriter().write("X-TenantID not present in the Request Header");
                response.setStatus(400);
                return false;
            }
            TenantContext.setCurrentTenant(tenantID);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        TenantContext.clear();
    }

}