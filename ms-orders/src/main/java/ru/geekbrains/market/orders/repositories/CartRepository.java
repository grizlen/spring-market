package ru.geekbrains.market.orders.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.market.orders.models.CartItem;

import java.util.List;
import java.util.UUID;

public interface CartRepository extends JpaRepository<CartItem, UUID> {
    List<CartItem> findAllByUserId(Long userId);
}
