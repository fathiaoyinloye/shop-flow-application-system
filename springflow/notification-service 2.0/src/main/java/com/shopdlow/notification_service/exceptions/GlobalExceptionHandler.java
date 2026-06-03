package com.shopdlow.notification_service.exceptions;

import com.shopdlow.notification_service.dtos.responses.NotificationFlowResponse;
import jakarta.servlet.ServletException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.security.sasl.AuthenticationException;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<NotificationFlowResponse<?>> handleValidationException(MethodArgumentNotValidException ex) {
        NotificationFlowResponse<?> response = new NotificationFlowResponse<>();
        response.setMessage("Invalid request sent to the server");
        response.buildErrorResponse(List.of(ex.getMessage()));
        return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);

    }


    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<NotificationFlowResponse<?>> handleAuthenticationException(AuthenticationException ex) {
        NotificationFlowResponse<?> response = new NotificationFlowResponse<>();
        response.setMessage("Invalid request sent to the server");
        response.buildErrorResponse(List.of(ex.getMessage()));
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);

    }

    @ExceptionHandler(ServletException.class)
    public ResponseEntity<NotificationFlowResponse<?>> handleServletException(ServletException ex) {
        NotificationFlowResponse<?> response = new NotificationFlowResponse<>();
        response.setMessage("Invalid request sent to the server");
        response.buildErrorResponse(List.of(ex.getMessage()));
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);

    }

    @ExceptionHandler(NotificationFlowException.class)
    public ResponseEntity<NotificationFlowResponse<?>> handleNotificationFlowException(NotificationFlowException ex) {
        NotificationFlowResponse<?> response = new NotificationFlowResponse<>();
        response.setMessage("notification flow exception");
        response.buildErrorResponse(List.of(ex.getMessage()));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<NotificationFlowResponse<?>> handleValidationException(UsernameNotFoundException ex) {
        NotificationFlowResponse<?> response = new NotificationFlowResponse<>();
        response.setMessage("Invalid username or password");
        response.buildErrorResponse(List.of(ex.getMessage()));
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);

    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<NotificationFlowResponse<?>> handleValidationException(Exception ex) {
        NotificationFlowResponse<?> response = new NotificationFlowResponse<>();
        response.setMessage("Internal server error");
        response.buildErrorResponse(List.of(ex.getMessage()));
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
