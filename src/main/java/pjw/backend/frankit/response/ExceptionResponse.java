package pjw.backend.frankit.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ExceptionResponse {
    private HttpStatus status;
    private String message;

    public static ExceptionResponse newOne(HttpStatus status, String message){
        return new ExceptionResponse(status, message);
    }

    private ExceptionResponse(){}
    private ExceptionResponse(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
