package ru.geekbrains.market.auth.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.market.auth.models.AuthRequestDTO;
import ru.geekbrains.market.auth.models.AuthResponseDTO;
import ru.geekbrains.market.auth.services.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authservice;

    @PostMapping("/signup")
    public AuthResponseDTO signUp(@RequestBody AuthRequestDTO request) {
        return authservice.signUp(request);
    }
    @PostMapping("/login")
    public String logIn() {
        return "sign up";
    }
    @GetMapping("/logout")
    public void logOut() {
    }
}
