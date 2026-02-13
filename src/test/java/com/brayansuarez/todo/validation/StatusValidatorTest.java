package com.brayansuarez.todo.validation;

import com.brayansuarez.todo.dto.TaskCreateRequest;
import com.brayansuarez.todo.exception.TaskValidationException;

import com.brayansuarez.todo.model.TaskPriority;
import com.brayansuarez.todo.model.TaskStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class StatusValidatorTest {
    private StatusValidator validator;
    private TaskCreateRequest request;


    @BeforeEach
    void seUp(){
    validator= new StatusValidator();
    request= new TaskCreateRequest();
    request.setTitle("Tarea de prueba  para validar Status");
    request.setDueDate(LocalDate.now().plusDays(1));

    }

    void  statusNula_DebeLanzarExcepcion(){
        request.setPriority(null);
        TaskValidationException exception= assertThrows( TaskValidationException.class, ()-> validator.validate(request));
    }

    @Test
     void statusValida_NoDebeLanzarExcepcion(){
        request.setStatus(TaskStatus.IN_PROGRESS);
        assertDoesNotThrow(()->validator.validate(request));

    }
}
