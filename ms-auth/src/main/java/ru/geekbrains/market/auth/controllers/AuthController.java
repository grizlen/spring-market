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

    private final AuthService authService;

    @PostMapping("/signup")
    public AuthResponseDTO signUp(@RequestBody AuthRequestDTO request) {
        return authService.signUp(request);
    }

    @PostMapping("/login")
    public AuthResponseDTO logIn(@RequestBody AuthRequestDTO request) {
        return authService.logIn(request);
    }

    @GetMapping("/logout")
    public void logOut(@RequestHeader("Authorization") String token) {
        if (token != null) {
            authService.logOut(token);
        }
    }
}
