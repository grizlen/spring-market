package ru.geekbrains.market.route.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class OrderItemDTO {
    private long userId;
    private long productId;
    private String productTitle;
    private Integer quantity;
    private Float price;
    private Float amount;
    private LocalDateTime paid;
    private LocalDateTime delivered;
}
