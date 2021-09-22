package ru.geekbrains.market.products.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.geekbrains.market.core.exceptions.ResourceNotFoundException;
import ru.geekbrains.market.products.models.Product;
import ru.geekbrains.market.route.dtos.ProductDTO;
import ru.geekbrains.market.products.repoitories.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductService {

    private static final int PAGE_SIZE = 2;

    private final ProductRepository productRepository;

    public Page<ProductDTO> getAll(Integer page) {
        PageRequest pr = PageRequest.of(page, PAGE_SIZE);
        return productRepository.findAll(pr).map(this::productToDTO);
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
