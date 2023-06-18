package uz.es.company.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import uz.es.company.exception.AuthenticationFailedException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {AuthenticationFailedException.class})
    public ResponseEntity<String> authExceptionHandler(AuthenticationFailedException e) {
        return ResponseEntity.status(401).body(e.getMessage());
    }
}
