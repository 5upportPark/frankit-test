package pjw.backend.frankit.exception;

import org.springframework.http.HttpStatus;

public class DataNotFoundException extends RuntimeException{
    public HttpStatus getStatus(){
        return HttpStatus.FORBIDDEN;
    }

    public DataNotFoundException(){
        super("존재하지 않는 정보입니다.");
    }
    public DataNotFoundException(Exception e){
        super(e);
    }
    public DataNotFoundException(String message){
        super(message);
    }
}
