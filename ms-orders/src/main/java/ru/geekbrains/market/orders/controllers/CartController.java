package ru.geekbrains.market.orders.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.market.core.models.UserInfo;
import ru.geekbrains.market.orders.models.ProductRequestDTO;
import ru.geekbrains.market.orders.services.CartService;
import ru.geekbrains.market.route.dtos.CartItemDto;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public List<CartItemDto> getCart() {
        UserInfo userInfo = currentUser();
        if (userInfo == null) {
            return Collections.emptyList();
        }
        return cartService.getCart(userInfo.getId());
    }

    @PostMapping
    public void addProduct(@RequestBody ProductRequestDTO request) {
        UserInfo userInfo = currentUser();
        if (userInfo != null) {
            cartService.addProduct(userInfo.getId(), request);
        }
    }

    private UserInfo currentUser() {
        UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userInfo;
    }
}
