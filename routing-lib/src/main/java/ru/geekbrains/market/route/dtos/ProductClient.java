package ru.geekbrains.market.route.dtos;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("ms-products")
public interface ProductClient {
    @GetMapping("/api/v1/products/{id}")
    public ProductDTO getById(@PathVariable Long id);
}
