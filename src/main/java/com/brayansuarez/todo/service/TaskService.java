package com.brayansuarez.todo.service;

import com.brayansuarez.todo.model.Task;
import com.brayansuarez.todo.dto.TaskCreateRequest;
import com.brayansuarez.todo.dto.TaskUpdateRequest;
import com.brayansuarez.todo.dto.TaskResponse;
import com.brayansuarez.todo.repository.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
public interface TaskService {
    TaskResponse create(TaskCreateRequest request);
    Page<TaskResponse> findAll(Pageable pageable, String status, String priority, String dueBefore);

    TaskResponse findById(long id);
    TaskResponse update(long id, TaskUpdateRequest request);
    void delete(long id);


}
