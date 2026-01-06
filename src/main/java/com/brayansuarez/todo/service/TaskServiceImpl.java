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
import java.util.ArrayList;
import java.util.List;
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
    @Transactional
    public List<TaskResponse> createBatch(List<TaskCreateRequest>requests){
if (requests == null || requests.isEmpty()){
    throw new IllegalArgumentException("lista vacia");

}
if (requests.size()>50){
    throw new IllegalArgumentException("mex 50 tares");
    }
//convertir request a entidades
        List<Task> tasks= new ArrayList<>();
for(TaskCreateRequest request:requests){
    Task task= new Task();
    task.setTitle(request.getTitle());
    task.setDescription(request.getDescription());
    task.setPriority(request.getPriority());
    task.setStatus(request.getStatus());
    task.setDueDate(request.getDueDate());
tasks.add(task);
}
List<Task> saveTasks = repository.saveAll(tasks);
    List<TaskResponse> responses= new ArrayList<>();
    for(Task task : saveTasks){
        responses.add(TaskResponse.fromEntity(task));
    }
    return   responses;
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