package ru.geekbrains.market.products.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.market.core.exceptions.ResourceNotFoundException;
import ru.geekbrains.market.products.models.Product;
import ru.geekbrains.market.route.ProductDTO;
import ru.geekbrains.market.products.repoitories.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductDTO> getAll() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::productToDTO).collect(Collectors.toList());
    }

    public ProductDTO getById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product with id: " + id + " not found.")
        );
        return productToDTO(product);
    }

    private ProductDTO productToDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setTitle(product.getTitle());
        dto.setPrice(product.getPrice());
        return dto;
    }
}
