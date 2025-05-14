package com.example.demo.patterns;

@lombok.Data
@lombok.NoArgsConstructor
public abstract class ApiEntity {
    /**
     * In depends on serialization and client needs the $meta can be mapped to
     * other name like "$" or "_" or "_meta".
     * For instance, the svelte client uses "$" as prefix svelte state resolving.
     */
    public ApiEntityMeta $meta;
}
