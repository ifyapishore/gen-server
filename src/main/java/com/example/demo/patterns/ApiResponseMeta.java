package com.example.demo.patterns;

@lombok.Data                       // Combines @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor
@lombok.NoArgsConstructor
public class ApiResponseMeta {

    // helper methods for service side tracking and analytics
    boolean isValidIdempotencyKey(String idempotencyKey) {
        return true;
    }
}
