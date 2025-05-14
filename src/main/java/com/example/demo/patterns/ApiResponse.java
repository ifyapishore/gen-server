package com.example.demo.patterns;

import com.example.demo.apimodel.StatSummary;

@lombok.Data
@lombok.NoArgsConstructor
public class ApiResponse<T> {
    public String idempotencyKey;
    public ApiResponseStatus status;
    public T d;

    public static <T> ApiResponse<T> of(T entity) {
        ApiResponse<T> response = new ApiResponse<>();
        //
        response.idempotencyKey = "nope";
        response.status = ApiResponseStatus.ok;
        response.d = entity;
        return response;
    }

    public static <T> ApiResponse<T> of(Exception e) {
        ApiResponse<T> response = new ApiResponse<>();
        response.status = ApiResponseStatus.fail;
        return response;
    }
}
