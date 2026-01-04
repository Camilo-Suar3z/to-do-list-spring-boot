package com.brayansuarez.todo.controller;

import com.brayansuarez.todo.dto.TaskResponse;
import com.brayansuarez.todo.dto.TaskUpdateRequest;
import com.brayansuarez.todo.dto.TaskCreateRequest;
import com.brayansuarez.todo.model.TaskStatus;
import com.brayansuarez.todo.service.TaskService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {
    public final TaskService service;

    public TaskController(TaskService service) {

        this.service = service;
    }

    @PostMapping
    public TaskResponse create(@Valid @RequestBody TaskCreateRequest request) {
        return service.create(request);
    }


    // Listar tareas con paginacion  + Fltros (status, priority, dueBefore)
    @GetMapping
    public Page<TaskResponse> findAll(Pageable pageable, @RequestParam(required = false) String status, @RequestParam(required = false) String prioritry, @RequestParam(required = false) String dueBefore) {

        return service.findAll(pageable, status, prioritry, dueBefore);


    }
    // obtener por id

    @GetMapping("/{id}")
    public TaskResponse findById(@PathVariable Long id) {
        return service.findById(id);

    }

    // Actualizar completa
    @PutMapping("/{id}")
    public TaskResponse update(@PathVariable Long id, @Valid @RequestBody TaskUpdateRequest request) {
        return service.update(id, request);
    }


    //eliminar
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }



//eliminar por status
@DeleteMapping("/by-status/{status}")
    public ResponseEntity<Map<String, Object>> deleteByStatus(@PathVariable TaskStatus status){
        long delete= service.deleteByStatus(status);
        return ResponseEntity.ok(Map.of("deletedCount", delete));
}


}
