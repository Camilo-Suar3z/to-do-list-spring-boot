package com.brayansuarez.todo.validation;


import com.brayansuarez.todo.dto.TaskCreateRequest;
import com.brayansuarez.todo.exception.TaskValidationException;
import org.springframework.stereotype.Component;

@Component

public class StatusValidator  implements TaskValidator{
    @Override
            public void validate(TaskCreateRequest request){
        System.out.println("Validando el Statud");
        if(request.getStatus()==null){
            throw new TaskValidationException("El Status es requerido ")
            System.out.println("se");

        }
        System.out.println("El status ha sido validada correctamente: " + request.getPriority());

    }


}
