package com.example.demo.apimodel;

import com.example.demo.patterns.ApiEntity;

@lombok.Data
@lombok.NoArgsConstructor
public class StatSummary extends ApiEntity {
    public String id;
    public String name;
    public String description;
}
