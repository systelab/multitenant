package com.werfen.inventory.features.product.service;

import com.werfen.inventory.core.tenant.TenantContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ProductServiceAspect {
    @Before("execution(* com.werfen.inventory.features.product.service.ProductService.*(..))&& target(productService) ")
    public void aroundExecution(JoinPoint pjp, ProductService productService) throws Throwable {
        org.hibernate.Filter filter = productService.getEntityManager().unwrap(Session.class).enableFilter("tenantFilter");
        filter.setParameter("tenantId", TenantContext.getCurrentTenant().orElse(null));
        filter.validate();
    }
}