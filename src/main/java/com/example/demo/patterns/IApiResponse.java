package com.example.demo.patterns;

@lombok.Data
@lombok.NoArgsConstructor
public class IApiResponse<T> {
    public String idempotencyKey;
    public ApiResponseStatus status;
    public T d;

    public static <T> IApiResponse<T> of(T entity) {
        IApiResponse<T> response = new IApiResponse<>();
        //
        response.idempotencyKey = "nope";
        response.status = ApiResponseStatus.ok;
        response.d = entity;
        return response;
    }

    public static <T> IApiResponse<T> of(Exception e) {
        IApiResponse<T> response = new IApiResponse<>();
        response.status = ApiResponseStatus.fail;
        return response;
    }
}
