package ru.geekbrains.market.auth.repositoies;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.market.auth.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
