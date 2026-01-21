package com.brayansuarez.todo.model;

import jakarta.persistence.*;
import  jakarta.validation.constraints.*;

import java.security.PrivateKey;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name= "tasks") // se puede cambiar el nombre

public class    Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Identity/serial en postgres
    private long id;


    @NotBlank(message = "el titulo es obligatorio")
    @Size(min = 3, max = 100, message = "el titulo debe tener entre 3 y 100 caracteres")
    @Column(nullable = false)
    private String title;


    @Size(min = 0, max = 500, message = "la descripcion no puede superar 500 carcteres")
    @Column(length = 500)
    private String description;

    @NotNull(message = "la prioridad es obligatoria")
    @Enumerated(EnumType.STRING) // guarda LOW/MEDIUM/HIGH como texto
    @Column(nullable = false, length = 10)
    private TaskPriority priority;
    @NotNull(message = "El estado es obligatorio")
    @Enumerated(EnumType.STRING)// guarda PENDING/IN_PROGRESS/DONE como texto
    @Column(name= "status", nullable = false, length = 12)
    private TaskStatus status;

    @FutureOrPresent(message = "la fecha debe de ser presente o futura")
    @NotNull(message = "la fecha debe de ser obligatoria ")
    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @Column(name= "created_at" ,nullable = false)
    private LocalDateTime createdAt;

    @Column(name= " updated_at", nullable = false)
    private LocalDateTime updatedAt;

@PrePersist
    protected void onCreate(){
    this.createdAt = LocalDateTime.now();
    this.updatedAt=  this.createdAt;

    if (this.status == null) this.status = TaskStatus.PENDING;
    if (this.priority == null) this.priority = TaskPriority.MEDIUM;
    }
@PreUpdate
        protected void onUpdate(){
    this.updatedAt= LocalDateTime.now();
    }

//construcotres
    public Task(){

    }
    public Task(long id, String title, String descripcion, TaskPriority priority, TaskStatus status, LocalDate dueDate, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.description = descripcion;
        this.priority = priority;
        this.status = status;
        this.dueDate = dueDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    //Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String descripcion) {
        this.description = descripcion;
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

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
