package com.example.demo.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Task in mongo repository.
 */
@Document
public class Task {
    @Id
    private String id;
    private String name;
    private TaskStatus status;
}