package ru.geekbrains.market.route.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CartItemDto {
    private Long productId;
    private String productTitle;
    private Integer quantity;
    private Float price;
}
