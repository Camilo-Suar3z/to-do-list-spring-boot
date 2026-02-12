package com.brayansuarez.todo.exception;
 import org.aspectj.bridge.IMessage;
 import org.springframework.http.HttpStatus;
 import org.springframework.http.ResponseEntity;
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



}

