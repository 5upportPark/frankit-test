package pjw.backend.frankit.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pjw.backend.frankit.entity.User;
import pjw.backend.frankit.repositoryImpl.UserRepositoryImpl;
import pjw.backend.frankit.request.LoginRequest;
import pjw.backend.frankit.response.LoginResponse;
import pjw.backend.frankit.util.JwtProvider;

@Service
public class UserService {
    private final UserRepositoryImpl userRepositoryImpl;

    public UserService(UserRepositoryImpl userRepositoryImpl) {
        this.userRepositoryImpl = userRepositoryImpl;
    }

    @Transactional
    public LoginResponse userLogin(LoginRequest req){
        User user = userRepositoryImpl.findAllByEmail(req.getEmail()).orElseThrow(RuntimeException::new);

        if (!req.getPassword().equals(user.getPassword())){
            return LoginResponse.builder()
                    .token(null)
                    .message("로그인 실패")
                    .build();
        }

        String token = JwtProvider.create(user.getId(), user.getName(), user.getEmail());
        return LoginResponse.builder()
                .token(token)
                .message("로그인 성공")
                .build();
    }
}
