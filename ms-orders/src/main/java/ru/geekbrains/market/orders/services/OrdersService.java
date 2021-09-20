package ru.geekbrains.market.orders.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.market.core.exceptions.ResourceNotFoundException;
import ru.geekbrains.market.orders.models.CartItem;
import ru.geekbrains.market.orders.models.OrderItem;
import ru.geekbrains.market.route.dtos.OrderItemDTO;
import ru.geekbrains.market.orders.repositories.CartRepository;
import ru.geekbrains.market.orders.repositories.OrdersRepository;
import ru.geekbrains.market.route.dtos.ProductClient;
import ru.geekbrains.market.route.dtos.ProductDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrdersService {

    private final OrdersRepository ordersRepository;
    private final CartRepository cartRepository;
    private final ProductClient productClient;

    public List<OrderItemDTO> getAll(Long userId) {
        List<OrderItem> items = ordersRepository.findAllByUserId(userId);
        List<OrderItemDTO> result = items.stream().map(this::orderItemToDTO).collect(Collectors.toList());
        return result;
    }

    public void addCartItem(Long cartId) {
        CartItem cartItem = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart with id: " + cartId + " not found."));
        ProductDTO productDTO = productClient.getById(cartItem.getProductId());
        OrderItem orderItem = new OrderItem();
        orderItem.setUserId(cartItem.getUserId());
        orderItem.setProductId(cartItem.getProductId());
        orderItem.setQuantity(cartItem.getQuantity());
        orderItem.setPrice(productDTO.getPrice());
        orderItem.setAmount(productDTO.getPrice() * orderItem.getQuantity());
        ordersRepository.save(orderItem);
        cartRepository.delete(cartItem);
    }

    private OrderItemDTO orderItemToDTO(OrderItem orderItem) {
        ProductDTO productDTO = productClient.getById(orderItem.getProductId());
        OrderItemDTO dto = new OrderItemDTO();
        dto.setUserId(orderItem.getUserId());
        dto.setProductId(orderItem.getProductId());
        dto.setProductTitle(productDTO.getTitle());
        dto.setQuantity(orderItem.getQuantity());
        dto.setPrice(productDTO.getPrice());
        dto.setAmount(dto.getQuantity() * dto.getPrice());
        return dto;
    }
}
