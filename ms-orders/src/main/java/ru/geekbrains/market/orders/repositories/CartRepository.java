package ru.geekbrains.market.orders.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.market.orders.models.CartItem;

import java.util.List;

public interface CartRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findAllByUserId(Long userId);
}
