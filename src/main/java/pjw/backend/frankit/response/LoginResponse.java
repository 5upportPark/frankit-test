package pjw.backend.frankit.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginResponse {
    private String token;
    private String message;

    private LoginResponse(){}

    @Builder
    public LoginResponse(String token, String message){
        this.token = token;
        this.message = message;
    }
}
