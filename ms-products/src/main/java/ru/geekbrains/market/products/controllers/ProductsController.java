package ru.geekbrains.market.products.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.market.route.dtos.ProductDTO;
import ru.geekbrains.market.products.services.ProductService;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductsController {

    private final ProductService productService;

    @GetMapping
    public Page<ProductDTO> getAll(@RequestParam(name = "page", defaultValue = "0") Integer page) {
        return productService.getAll(page < 0 ? 0 : page);
    }

    @GetMapping("/{id}")
    public ProductDTO getById(@PathVariable Long id) {
        return productService.getById(id);
    }
}
