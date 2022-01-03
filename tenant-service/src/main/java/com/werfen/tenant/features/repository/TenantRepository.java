package com.werfen.tenant.features.repository;

import com.werfen.tenant.features.model.Tenant;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RequiredArgsConstructor
@Repository
public class TenantRepository {

    private static final String KEY = "TENANT";

    private final ReactiveRedisTemplate<String, Tenant> template;

    public Mono<Tenant> save(Tenant tenant) {
        return template.opsForHash().put(KEY, tenant.getId().toString(), tenant).map(a -> tenant);
    }

    public Mono<Tenant> findById(UUID id) {
        return template.opsForHash().get(KEY, id.toString()).cast(Tenant.class);
    }

    public Mono<Long> deleteById(UUID id) {
        return template.opsForHash().remove(KEY, id.toString());
    }
}