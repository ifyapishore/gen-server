package com.example.demo.patterns;

import java.util.Set;

@lombok.Data                       // Combines @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor
@lombok.NoArgsConstructor
public class ApiEntityMeta {
    public String idempotencyKey;
    /**
     * If specified, returns the set of user permissions for this entity.
     */
    public Set<ApiPermission> permissions;
}
