package ru.geekbrains.marchmarket.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.marchmarket.utils.Cart;
import ru.geekbrains.marchmarket.utils.CartItem;

import java.math.BigDecimal;
import java.util.List;


@NoArgsConstructor
@Data
public class CartDto {


    private List<CartItemDto> items;
    private BigDecimal totalPrice;




}
