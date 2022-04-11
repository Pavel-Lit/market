package ru.geekbrains.marchmarket.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemDto {

    private Long productId;
    private BigDecimal price;
    private int quantity;
    private String productTitle;
    private  BigDecimal pricePerProduct;
}
