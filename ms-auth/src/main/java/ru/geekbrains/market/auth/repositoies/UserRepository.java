package ru.geekbrains.market.auth.repositoies;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.market.auth.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);
}
