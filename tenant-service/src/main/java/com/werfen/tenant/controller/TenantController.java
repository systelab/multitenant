package com.werfen.tenant.controller;


import com.werfen.tenant.model.Tenant;
import com.werfen.tenant.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
public class TenantController {

    @Autowired
    private TenantService tenantService;

    @PostMapping("tenants/")
    public Mono<Tenant> put(@RequestBody Tenant tenant) {

        return tenantService.save(tenant);
    }

    @GetMapping("tenants/{id}")
    public Mono<Tenant> get(@PathVariable("id") UUID id) {

        return tenantService.get(id);
    }

    @DeleteMapping("tenants/{id}")
    public Mono<Void> delete(@PathVariable("id") UUID id) {
        return tenantService.delete(id);
    }
}