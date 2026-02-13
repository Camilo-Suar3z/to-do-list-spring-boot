package com.brayansuarez.todo.validation;

import com.brayansuarez.todo.dto.TaskCreateRequest;
import com.brayansuarez.todo.exception.TaskValidationException;
import org.springframework.stereotype.Component;

@Component

public class PriorityValidator implements TaskValidator {

    @Override
    public void validate (TaskCreateRequest request) {
        System.out.println("Validando prioridad...");

        if (request.getPriority()==null){
            throw new TaskValidationException("La prioridad es requerida");
        }

        System.out.println("La prioridad ha sido validada correctamente: " + request.getPriority());




    }
}
