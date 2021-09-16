package ru.geekbrains.market.products;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductsController {
    @Value("${jwt.secret}")
    private String jwt;


    @GetMapping
    public String getAll() {
        return "all products " + jwt;
    }
}
