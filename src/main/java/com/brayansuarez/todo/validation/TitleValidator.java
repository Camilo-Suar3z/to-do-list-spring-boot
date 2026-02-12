package com.brayansuarez.todo.validation;

import com.brayansuarez.todo.dto.TaskCreateRequest;
import com.brayansuarez.todo.exception.TaskValidationException;
import org.springframework.stereotype.Component;

@Component
public class TitleValidator implements TaskValidator {
    @Override
    public void validate(TaskCreateRequest request){

        System.out.println("Validando titulo...");

        if(request.getTitle()== null || request.getTitle().trim().isEmpty()){

            throw new TaskValidationException("El título es requerido");

        }
        if (request.getTitle().length()>120){
            throw new TaskValidationException("no puede superar 120 caracteres"+ request.getTitle().length());
        }
        System.out.println("Titulo valido " +  request.getTitle());
    }


}
