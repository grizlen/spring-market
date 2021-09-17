package ru.geekbrains.market.auth.repositoies;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.market.auth.models.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}
