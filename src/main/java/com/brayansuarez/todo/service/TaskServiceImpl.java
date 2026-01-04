package com.brayansuarez.todo.service;

import  com.brayansuarez.todo.dto.TaskCreateRequest;
import com.brayansuarez.todo.dto.TaskResponse;
import com.brayansuarez.todo.dto.TaskUpdateRequest;
import  com.brayansuarez.todo.model.Task;
import com.brayansuarez.todo.model.TaskStatus;
import com.brayansuarez.todo.repository.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.NoSuchElementException;


@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository repository;

    public TaskServiceImpl(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public TaskResponse create(TaskCreateRequest request) {
        // Regla adicional: dueDate no puede ser pasado (además de @FutureOrPresent en DTO)
        if (request.getDueDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("la fechalimite no puede estar en el  pasado ");
        }
        Task entity = new Task();
        entity.setTitle(request.getTitle());
        entity.setDescription(request.getDescription());
        entity.setPriority(request.getPriority());
        entity.setStatus(request.getStatus());
        entity.setDueDate(request.getDueDate());

        Task save = repository.save(entity);
        return TaskResponse.fromEntity(save);
    }


    @Override
    public Page<TaskResponse> findAll(Pageable pageable, String status, String priority, String dueBefore) {
        // MVP: primero listamos todo con paginación
        // Luego podemos agregar filtros reales con Specifications o Query Methods
        Page<Task> page = repository.findAll(pageable);
        return page.map(TaskResponse::fromEntity);
    }

    @Override
    public TaskResponse findById(long id) {

        Task task = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Tarea no encontrada" + id));

        return TaskResponse.fromEntity(task);

    }

    @Override
    public TaskResponse update(long id, TaskUpdateRequest request) {
        Task task = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Tarea no encontrada: " + id));

        if (request.getDueDate() != null && request.getDueDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha límite no puede estar en el pasado.");}

            task.setTitle(request.getTitle());
            task.setDescription(request.getDescription());
            task.setPriority(request.getPriority());
            task.setStatus(request.getStatus());
            task.setDueDate(request.getDueDate());

            Task saved = repository.save(task);
        return TaskResponse.fromEntity(saved);
    }
    @Override
    public void delete(long id){
        if(!repository.existsById(id)){
            throw new NoSuchElementException("TAREA NO ECONTRADA "+ id);

        }
        repository.deleteById(id);
    }
    @Override
    @Transactional
    public long deleteByStatus(TaskStatus status){
        return repository.deleteByStatus(status);
    }


}