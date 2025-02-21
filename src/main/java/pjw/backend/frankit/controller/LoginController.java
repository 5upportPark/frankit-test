package pjw.backend.frankit.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pjw.backend.frankit.request.LoginRequest;
import pjw.backend.frankit.response.LoginResponse;
import pjw.backend.frankit.service.UserService;

@RestController
@RequestMapping("/login")
public class LoginController {
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public LoginResponse login(@RequestBody @Valid LoginRequest req){
        return userService.userLogin(req);
    }
}
