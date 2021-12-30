package com.werfen.tenant.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.UUID;

@RedisHash("tenants")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tenant {

    @Id
    private UUID id;
    private String name;
    private String schema;
}
