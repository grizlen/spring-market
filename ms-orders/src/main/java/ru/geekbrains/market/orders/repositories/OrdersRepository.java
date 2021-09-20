package ru.geekbrains.market.orders.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.market.orders.models.OrderItem;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findAllByUserId(Long userId);
}
