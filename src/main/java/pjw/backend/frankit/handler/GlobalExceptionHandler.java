package pjw.backend.frankit.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pjw.backend.frankit.exception.TokenException;
import pjw.backend.frankit.response.ExceptionResponse;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(TokenException.class)
    public ResponseEntity<ExceptionResponse> tokenExceptionHandler(TokenException e){
        ExceptionResponse res = ExceptionResponse.newOne(e.getStatus(), e.getMessage());
        return ResponseEntity.status(e.getStatus()).body(res);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> methodArgumentExceptionHandler(MethodArgumentNotValidException e){
        BindingResult br = e.getBindingResult();
        List<ExceptionResponse.FieldError> errorList = br.getFieldErrors().stream().map(err-> new ExceptionResponse.FieldError(err.getField(), err.getDefaultMessage())).toList();
        ExceptionResponse res = ExceptionResponse.newOne(HttpStatus.BAD_REQUEST, e.getMessage(), errorList);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> defaultHandler(Exception e){
        ExceptionResponse res = ExceptionResponse.newOne(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
    }
}
