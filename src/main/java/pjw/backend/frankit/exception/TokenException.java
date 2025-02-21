package pjw.backend.frankit.exception;

import org.springframework.http.HttpStatus;

public class TokenException extends RuntimeException{

    public HttpStatus getStatus(){
        return HttpStatus.UNAUTHORIZED;
    }

    public TokenException(){
        super();
    }
    public TokenException(Exception e){
        super(e);
    }
    public TokenException(String message){
        super(message);
    }
}
