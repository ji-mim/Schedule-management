package com.example.schedulemanagement.exception;

import com.example.schedulemanagement.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

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
}