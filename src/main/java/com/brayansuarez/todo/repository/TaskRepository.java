package com.brayansuarez.todo.repository;


 import com.brayansuarez.todo.model.Task;
 import com.brayansuarez.todo.model.TaskStatus;
 import org.springframework.data.jpa.repository.JpaRepository;
 import org.springframework.stereotype.Repository;
public interface TaskRepository extends JpaRepository<Task, Long> {

 long deleteByStatus(TaskStatus status);





}
