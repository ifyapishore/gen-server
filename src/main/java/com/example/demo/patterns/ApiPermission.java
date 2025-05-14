package com.example.demo.patterns;

import java.util.Set;

public enum ApiPermission {
    edit,
    delete,
    archive,
    forward;

    public static Set<ApiPermission> managerRole() {
        return Set.of(edit, delete, archive, forward);
    }
}
