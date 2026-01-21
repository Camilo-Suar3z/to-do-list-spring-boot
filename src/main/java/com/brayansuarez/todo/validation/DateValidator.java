package com.brayansuarez.todo.validation;

import com.brayansuarez.todo.dto.TaskCreateRequest;
import org.springframework.stereotype.Component;
@Component
public class DateValidator implements   TaskValidator{
    @Override
    public void validate(TaskCreateRequest request) {
        System.out.println("validando fecha");

        if(request.getDueDate()==null){
            throw new IllegalArgumentException("la fecha limite es requerida");

        }
        if (request.getDueDate().isBefore(java.time.LocalDate.now())){
            throw new IllegalArgumentException("La fecha limite debe de ser futura");
        }
        System.out.println("la fehca es  valida "+ request.getDueDate());

    }
}
