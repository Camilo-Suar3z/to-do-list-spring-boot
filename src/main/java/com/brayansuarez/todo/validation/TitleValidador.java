package com.brayansuarez.todo.validation;

import com.brayansuarez.todo.dto.TaskCreateRequest;
import org.springframework.stereotype.Component;
@Component
public class TitleValidador implements TaskValidator {
    @Override
    public void validate(TaskCreateRequest request){

        System.out.println("Validando titulo...");

        if(request.getTitle()== null || request.getTitle().trim().isEmpty()){

            throw new IllegalArgumentException("el titulo es reuqerido");

        }
        if (request.getTitle().length()>120){
            throw new IllegalArgumentException(" El título no puede superar 120 caracteres. Longitud actual:  "+ request.getTitle());
        }
        System.out.println("Titulo valido" +  request.getTitle());
    }


}
