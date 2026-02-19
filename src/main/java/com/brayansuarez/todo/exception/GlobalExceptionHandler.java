    package com.brayansuarez.todo.exception;
     import org.aspectj.bridge.IMessage;
     import org.springframework.http.HttpStatus;
     import org.springframework.http.ResponseEntity;
     import org.springframework.http.converter.HttpMessageNotReadableException;
     import org.springframework.web.HttpMediaTypeException;
     import org.springframework.web.bind.annotation.ControllerAdvice;
     import org.springframework.web.bind.annotation.ExceptionHandler;

     import java.rmi.MarshalledObject;
     import java.time.LocalDate;
     import java.time.LocalDateTime;
     import java.util.HashMap;
     import java.util.Map;

     @ControllerAdvice

    public class GlobalExceptionHandler {

    @ExceptionHandler(TaskValidationException.class)
         public ResponseEntity<Map<String,Object>> handleTaskValidationExeption(TaskValidationException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("error", "validation error");
        response.put("message", ex.getMessage());
        response.put("path", "api/tasks");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);


    }

         @ExceptionHandler(HttpMessageNotReadableException.class)
         public ResponseEntity<Map<String, Object>> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
             Map<String, Object> response = new HashMap<>();
             response.put("timestamp", LocalDateTime.now());
             response.put("Status", HttpStatus.BAD_REQUEST.value());
             response.put("erro", "validator error");

             String message = ex.getMessage();
             String customMessage = "Formato de JSON inválido";
             if (message.contains("TaskStatus")) {
                 customMessage = "El estado debe ser uno de: PENDING, IN_PROGRESS, DONE";
             } else if (message.contains("TaskPriority")) {
                 customMessage = "La prioridad debe ser uno de: LOW, MEDIUM, HIGH";
             } else if (message.contains("LocalDate")) {

                 customMessage = "La fecha debe tener formato YYYY-MM-DD (ej: 2026-12-31)";
             }


             response.put("message", customMessage);
             response.put("path", "api/tasks");
             return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

         }


    }

