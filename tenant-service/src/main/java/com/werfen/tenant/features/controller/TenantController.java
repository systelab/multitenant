package com.werfen.tenant.features.controller;


import com.werfen.tenant.features.model.Tenant;
import com.werfen.tenant.features.service.TenantService;
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
    public Mono<Boolean> delete(@PathVariable("id") UUID id) {
        return tenantService.delete(id);
    }
}