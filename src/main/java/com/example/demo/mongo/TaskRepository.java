package com.example.demo.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * MongoDB repository for managing tasks.
 */
@Repository
public interface TaskRepository extends MongoRepository<Task, Id> {
    /**
     * Find a task by its ID.
     *
     * @param id the ID of the task
     * @return an Optional containing the task if found, or empty if not found
     */
    Optional<Task> findById(Id id);

    /**
     * Find all tasks with a specific status.
     *
     * @param status the status of the tasks to find
     * @return a list of tasks with the specified status
     */
    List<Task> findByStatus(TaskStatus status);
}