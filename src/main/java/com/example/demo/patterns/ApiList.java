package com.example.demo.patterns;

import java.util.List;

public abstract class ApiList<ListItem extends ApiEntity> {
    public List<ListItem> d;
    public ApiListMeta $meta;
}
