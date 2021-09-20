package ru.geekbrains.market.orders.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.market.core.models.UserInfo;
import ru.geekbrains.market.route.dtos.OrderItemDTO;
import ru.geekbrains.market.orders.services.OrdersService;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrdersController {

    private final OrdersService ordersService;

    @GetMapping
    public List<OrderItemDTO> getAll() {
        UserInfo userInfo = currentUser();
        if (userInfo == null) {
            return Collections.emptyList();
        }
        return ordersService.getAll(userInfo.getId());
    }

    @PostMapping("/{cartid}")
    public void addCartItem(@PathVariable(name = "cartid") Long cartId) {
        ordersService.addCartItem(cartId);
    }

    private UserInfo currentUser() {
        UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userInfo;
    }
}
