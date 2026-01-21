package com.brayansuarez.todo.test;
import com.brayansuarez.todo.dto.TaskCreateRequest;
import com.brayansuarez.todo.validation.DateValidator;
import com.brayansuarez.todo.validation.TaskValidator;
import com.brayansuarez.todo.validation.TitleValidador;

public class TestValidation {
 public static void main(String [] arg){
     // create request to test
     TaskCreateRequest request= new TaskCreateRequest();
     request.setTitle("mi tarea");
     request.setDueDate(java.time.LocalDate.now().plusDays(1));
     TaskValidator dateValidator= new DateValidator();
     TaskValidator titleValidator= new TitleValidador();

      try   {
dateValidator.validate(request);
titleValidator.validate(request);
          System.out.println("✅ Todas las validaciones pasaron!");
      } catch (Exception e){
          System.out.println("❌ Error: " + e.getMessage());
      }
 }

}
