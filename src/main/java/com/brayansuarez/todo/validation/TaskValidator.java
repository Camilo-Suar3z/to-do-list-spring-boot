package com.brayansuarez.todo.validation;
 import  com.brayansuarez.todo.dto.TaskCreateRequest;
public interface TaskValidator {
    void validate(TaskCreateRequest request);
}
