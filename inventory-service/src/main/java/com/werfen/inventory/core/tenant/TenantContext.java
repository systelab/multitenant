package com.werfen.inventory.core.tenant;

import java.util.Optional;

public class TenantContext {
    private static ThreadLocal<String> currentTenant = new InheritableThreadLocal<>();

    private TenantContext() {
    }

    public static Optional<String> getCurrentTenant() {
        return Optional.ofNullable(currentTenant.get());
    }

    public static void setCurrentTenant(String tenant) {
        currentTenant.set(tenant);
    }

    public static void clear() {
        currentTenant.remove();
    }
}
