package controllers.exceptions;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import service.exceptions.DatabaseException;
import service.exceptions.ResourceNotFoundException;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
        String error = "Resource not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> databaseException(ResourceNotFoundException e, HttpServletRequest request){
        String error = "Database error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
