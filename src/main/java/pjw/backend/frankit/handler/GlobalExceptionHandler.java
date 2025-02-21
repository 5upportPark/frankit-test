package pjw.backend.frankit.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pjw.backend.frankit.exception.TokenException;
import pjw.backend.frankit.response.ExceptionResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(TokenException.class)
    public ResponseEntity<ExceptionResponse> tokenExceptionHandler(TokenException e){
        ExceptionResponse res = ExceptionResponse.newOne(e.getStatus(), e.getMessage());
        return ResponseEntity.status(e.getStatus()).body(res);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> defaultHandler(Exception e){
        return null;
    }
}
