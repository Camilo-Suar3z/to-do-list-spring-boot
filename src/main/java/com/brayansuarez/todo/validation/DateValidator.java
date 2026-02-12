package com.brayansuarez.todo.validation;

import com.brayansuarez.todo.dto.TaskCreateRequest;
import com.brayansuarez.todo.exception.TaskValidationException;
import org.springframework.stereotype.Component;
import java.time.LocalDate;@Component
public class DateValidator implements TaskValidator {
    @Override
    public void validate(TaskCreateRequest request) {
        System.out.println("validando fecha");

        if(request.getDueDate() == null) {
            throw new TaskValidationException("La fecha límite es requerida");  // ✅ CORREGIDO
        }

        if (request.getDueDate().isBefore(java.time.LocalDate.now())) {
            throw new TaskValidationException("La fecha límite debe ser futura");  // ✅ CORREGIDO
        }

        System.out.println("la fecha es válida " + request.getDueDate());
    }
}