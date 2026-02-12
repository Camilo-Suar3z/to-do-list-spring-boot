package com.brayansuarez.todo.validation;

import com.brayansuarez.todo.dto.TaskCreateRequest;
import com.brayansuarez.todo.exception.TaskValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class DateValidatorTest {

    private DateValidator dateValidator;
    private TaskCreateRequest request;

    @BeforeEach
    void setUp() {
        dateValidator = new DateValidator();
        request = new TaskCreateRequest();
        request.setTitle("Tarea de prueba");
    }

    @Test
    void fechaFutura_NoDebeLanzarExcepcion() {
        request.setDueDate(LocalDate.now().plusDays(1));
        assertDoesNotThrow(() -> dateValidator.validate(request));
    }

    @Test
    void fechaPasada_DebeLanzarTaskValidationException() {
        request.setDueDate(LocalDate.now().minusDays(1));

        TaskValidationException exception = assertThrows(
                TaskValidationException.class,
                () -> dateValidator.validate(request)
        );

        assertEquals("La fecha límite debe ser futura", exception.getMessage());
    }

    @Test
    void fechaNula_DebeLanzarTaskValidationException() {
        request.setDueDate(null);

        TaskValidationException exception = assertThrows(
                TaskValidationException.class,
                () -> dateValidator.validate(request)
        );

        assertEquals("La fecha límite es requerida", exception.getMessage());
    }
}