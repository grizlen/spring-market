package ru.geekbrains.market.products.repoitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.market.products.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
