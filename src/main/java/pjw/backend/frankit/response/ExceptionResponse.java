package pjw.backend.frankit.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
public class ExceptionResponse {
    private HttpStatus status;
    private String message;
    private List<FieldError> fieldErrors;

    public static ExceptionResponse newOne(HttpStatus status, String message){
        return new ExceptionResponse(status, message, null);
    }

    public static ExceptionResponse newOne(HttpStatus status, String message, List<FieldError> fieldErrors){
        return new ExceptionResponse(status, message, fieldErrors);
    }

    private ExceptionResponse(){}
    private ExceptionResponse(HttpStatus status, String message, List<FieldError> fieldErrors) {
        this.status = status;
        this.message = message;
        this.fieldErrors = fieldErrors;
    }

    @Getter
    public static class FieldError{
        private String field;
        private String message;

        private FieldError(){}
        public FieldError(String field, String message){
            this.field = field;
            this.message = message;
        }
    }
}
