package com.brayansuarez.todo.validation;
import com.brayansuarez.todo.dto.TaskCreateRequest;
import com.brayansuarez.todo.exception.TaskValidationException;
import  org.junit.jupiter.api.BeforeEach;
    import org.junit.jupiter.api.Test;
import org.springframework.test.context.bean.override.convention.TestBean;

import java.time.LocalDate;
    import static org.junit.jupiter.api.Assertions.*;
public class TitleValidatorTest {
    private TitleValidator  titleValidator;
    private TaskCreateRequest request;


    @BeforeEach
     void setUp(){
titleValidator=new TitleValidator();
request=new TaskCreateRequest();
request.setDueDate(LocalDate.now().plusDays(1));

    }

    @Test
      void tituloVacio_DebeLanzarTaskValidationException(){

        request.setTitle("");

        TaskValidationException exception= assertThrows(TaskValidationException.class, ()-> titleValidator.validate(request));
        assertEquals("El título es requerido", exception.getMessage());
    }


    @Test
    void tituloNulo_DebeLanzarTaskValidationException() {
        // 2. PREPARAR
        request.setTitle(null);

        TaskValidationException exception= assertThrows(TaskValidationException.class, ()-> titleValidator.validate(request));
        assertEquals("El título es requerido", exception.getMessage());
}
@Test
    void tituloExcedeLongitud_DebeLanzarTaskValidationException() {
        // 2. PREPARAR: título de 121 caracteres
        String tituloLargo = "a".repeat(121);
        System.out.println( "ATENCION!!!!!" + tituloLargo.length());
        request.setTitle(tituloLargo);

        // 3. EJECUTAR Y VERIFICAR
        TaskValidationException exception = assertThrows(
                TaskValidationException.class,
                () -> titleValidator.validate(request)
        );

    System.out.println("Mensaje completo:  " + exception.getMessage());
        // 4. VERIFICAR mensaje (puede ser parcial)
        assertTrue(exception.getMessage().contains("no puede superar 120 caracteres"));

            assertTrue(exception.getMessage().contains("121"));
    }
    @Test
    void tituloValido_NoDebeLanzarExcepcion() {
        // 2. PREPARAR
        request.setTitle("Mi tarea importante");

        // 3. EJECUTAR Y VERIFICAR
        assertDoesNotThrow(() -> titleValidator.validate(request));
    }

    }

