package ru.geekbrains.market.orders.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.market.orders.models.CartItem;
import ru.geekbrains.market.orders.models.ProductRequestDTO;
import ru.geekbrains.market.orders.repositories.CartRepository;
import ru.geekbrains.market.route.dtos.CartItemDto;
import ru.geekbrains.market.route.dtos.ProductClient;
import ru.geekbrains.market.route.dtos.ProductDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ProductClient productClient;

    public List<CartItemDto> getCart(Long userId) {
        return cartRepository.findAllByUserId(userId)
                .stream()
                .map(this::cartItemToDto)
                .collect(Collectors.toList());
    }

    public void addProduct(Long userId, ProductRequestDTO request) {
        List<CartItem> items = cartRepository.findAllByUserId(userId)
                .stream()
                .filter(cartItem -> cartItem.getProductId() == request.getId())
                .collect(Collectors.toList());
        CartItem item;
        if (items.isEmpty()) {
            item = new CartItem();
            item.setUserId(userId);
            item.setProductId(request.getId());
            item.setQuantity(1);
        } else {
            item = items.get(0);
            if (items.size() > 1) {
                while (items.size() > 1) {
                    CartItem removed = items.remove(1);
                    item.setQuantity(item.getQuantity() + removed.getQuantity());
                    cartRepository.delete(removed);
                }
            } else {
                item.setQuantity(item.getQuantity() + 1);
            }
        }
        cartRepository.save(item);
    }

    private CartItemDto cartItemToDto(CartItem Item) {
        ProductDTO productDTO = productClient.getById(Item.getProductId());
        CartItemDto dto = new CartItemDto();
        dto.setProductId(Item.getProductId());
        dto.setProductTitle(productDTO.getTitle());
        dto.setQuantity(Item.getQuantity());
        dto.setPrice(productDTO.getPrice());
        return dto;
    }
}
