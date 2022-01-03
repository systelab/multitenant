package com.werfen.tenant.features.service;

import com.werfen.tenant.features.model.Tenant;
import com.werfen.tenant.features.repository.TenantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TenantService {

    private final TenantRepository repo;

    public Mono<Tenant> save(Tenant tenant) {
        return repo.save(tenant);
    }

    public Mono<Tenant> get(UUID id) {
        return repo.findById(id);
    }

    public Mono<Boolean> delete(UUID id) {
        return repo.deleteById(id).map(value -> value == 1);
    }
}