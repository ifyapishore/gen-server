package com.example.demo.patterns;

@lombok.Data
@lombok.NoArgsConstructor
public class ApiListQuery {
    public String search;
    public int offset;
    public int limit;
}
