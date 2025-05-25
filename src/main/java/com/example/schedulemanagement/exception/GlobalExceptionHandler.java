package com.example.schedulemanagement.exception;

import com.example.schedulemanagement.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse> handleResponseStatus(ResponseStatusException ex, WebRequest request) {
        ErrorResponse response = new ErrorResponse(
                ex.getStatusCode().value(),
                ex.getReason(),
                request.getDescription(false).replace("uri=", "")
        );
        return ResponseEntity.status(ex.getStatusCode()).body(response);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleResponseStatus(MethodArgumentNotValidException ex, WebRequest request) {

        List<String> errorMessages = ex.getBindingResult().getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList();

        ErrorResponse response = new ErrorResponse(
                ex.getStatusCode().value(),
                String.join(", ", errorMessages),
                request.getDescription(false).replace("uri=", "")
        );
        return ResponseEntity.status(ex.getStatusCode()).body(response);
    }


}