package com.werfen.tenant.service;

import com.werfen.tenant.model.Tenant;
import com.werfen.tenant.repository.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class TenantService {

    @Autowired
    private TenantRepository repo;

    public Mono<Tenant> save(Tenant tenant){
        return Mono.just(repo.save(tenant));
    }


    public Mono<Tenant> get(UUID id){
        return Mono.justOrEmpty(repo.findById(id));
    }

    public Mono<Void> delete(UUID id){
        repo.deleteById(id);
        return Mono.empty();
    }
}