package com.werfen.tenant.repository;

import com.werfen.tenant.model.Tenant;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TenantRepository extends ReactiveCrudRepository<Tenant, UUID> {

}