package com.example.demo.patterns;

public class ApiListMeta {
    public int offset;
    public int size;
    public int total;
    public int pageSize;
    /**
     * Returns teh original query to the client
     */
    public ApiListQuery query;
}
