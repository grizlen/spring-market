package ru.geekbrains.market.auth.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.geekbrains.market.auth.models.AuthRequestDTO;
import ru.geekbrains.market.auth.models.AuthResponseDTO;
import ru.geekbrains.market.auth.models.Role;
import ru.geekbrains.market.auth.models.User;
import ru.geekbrains.market.auth.repositoies.RoleRepository;
import ru.geekbrains.market.auth.repositoies.UserRepository;
import ru.geekbrains.market.core.models.UserInfo;
import ru.geekbrains.market.core.services.ITokenService;

import java.util.Collections;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ITokenService tokenService;

    public AuthResponseDTO signUp(AuthRequestDTO authRequestDTO) {
        User user = new User();
        user.setLogin(authRequestDTO.getLogin());
        user.setPassword(passwordEncoder.encode(authRequestDTO.getPassword()));
        Role role = roleRepository.findByName("ROLE_USER").orElseThrow(() -> new RuntimeException("User role USER not found"));
        user.setRoles(Collections.singletonList(role));
        UserInfo userInfo = userToUserInfo(userRepository.save(user));
        return new AuthResponseDTO(tokenService.generateToken(userInfo));
    }

    private UserInfo userToUserInfo(User user) {
        UserInfo userInfo = new UserInfo();
        user.setId(user.getId());
        userInfo.setLogin(user.getLogin());
        userInfo.setRoles(user.getRoles().stream().map(role -> role.getName()).collect(Collectors.toList()));
        return userInfo;
    }
}
