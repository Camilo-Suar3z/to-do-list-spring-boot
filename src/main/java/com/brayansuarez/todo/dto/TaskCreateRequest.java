package com.brayansuarez.todo.dto;

import com.brayansuarez.todo.model.TaskPriority;
import com.brayansuarez.todo.model.TaskStatus;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class TaskCreateRequest {
@NotBlank(message = "el titulo es obligatorio")
    @Size(max= 120, message = "l título no debe superar 120 caracteres")
    private String title;

@Size(max=500, message = "la descripcion no debe superar 500 caracteres")
    private String description;

@NotNull(message = "la prioridad es obligatoria")
    private TaskPriority priority;

@NotNull(message = "el estado es obligatorio")
    private TaskStatus status;

@FutureOrPresent(message = "la fecha limite debe de ser presente o futura ")
    private LocalDate dueDate;



//getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueData(LocalDate dueData) {
        this.dueDate = dueData;
    }
}
