package com.lambda.FindMyGym.repository;

import com.lambda.FindMyGym.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface TaskRepository extends MongoRepository<Task, String> {
    List<Task> findBySeverity(int severity);

    @Query("{assignee: ?0 }")
    List<Task> getTasksByAssignee(String assignee);
}
