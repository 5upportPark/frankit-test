package pjw.backend.frankit.exception;

import org.springframework.http.HttpStatus;

public class CountLimitException extends RuntimeException{
    public HttpStatus getStatus(){
        return HttpStatus.CONFLICT;
    }

    public CountLimitException(){
        super();
    }
    public CountLimitException(Exception e){
        super(e);
    }
    public CountLimitException(String message){
        super(message);
    }
}
