package pjw.backend.frankit.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequest {
    @Email
    @NotEmpty(message = "이메일을 입력해주세요.")
    private String email;
    @NotEmpty(message = "비밀번호를 입력해주세요.")
    private String password;
}
