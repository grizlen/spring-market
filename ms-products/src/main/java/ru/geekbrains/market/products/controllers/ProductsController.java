package ru.geekbrains.market.products.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.market.route.ProductDTO;
import ru.geekbrains.market.products.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductsController {

    private final ProductService productService;

    @GetMapping
    public List<ProductDTO> getAll() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public ProductDTO getById(@PathVariable Long id) {
        return productService.getById(id);
    }
}
