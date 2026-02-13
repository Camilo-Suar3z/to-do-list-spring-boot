package com.brayansuarez.todo.validation;

import com.brayansuarez.todo.dto.TaskCreateRequest  ;
import com.brayansuarez.todo.exception.TaskValidationException;

import com.brayansuarez.todo.model.TaskPriority;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class PriorityValidatorTest {
private PriorityValidator validator;
private TaskCreateRequest request;

@BeforeEach
    void setUp(){

    validator = new PriorityValidator();
    request= new TaskCreateRequest();
    request.setTitle("Tarea de prueba  para validar el Priority");
    request.setDueDate(LocalDate.now().plusDays(1));

}
@Test
  void  prioridadNula_DebeLanzarExcepcion (){
request.setPriority(null);

TaskValidationException exception= assertThrows(
        TaskValidationException.class, ()-> validator.validate(request)
);

}

@Test
void prioridadValida_NoDebeLanzarExcepcion(){
    request.setPriority(TaskPriority.HIGH);
    assertDoesNotThrow(()-> validator.validate(request));
}

}
