package ru.geekbrains.marchmarket.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.marchmarket.converters.CartConverter;
import ru.geekbrains.marchmarket.dtos.CartDto;
import ru.geekbrains.marchmarket.services.CartService;
import ru.geekbrains.marchmarket.utils.Cart;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final CartConverter cartConverter;

    @GetMapping("/old-version")
    public Cart getCurrentCart() {
        return cartService.getCurrentCart();

    }

    @GetMapping("")
    public CartDto getCurrentCartDto(Cart cart) {
        return cartConverter.getCartDto(cartService.getCurrentCart());
    }

    @GetMapping("/add/{productId}")
    public void addProductToCart(@PathVariable Long productId) {
        cartService.addToCart(productId);
    }

    @GetMapping("/remove/{id}")
    public void removeOneProductFromCart(@PathVariable Long id) {
        cartService.removeById(id);
    }

    @GetMapping("/clear")
    public void clearCart() {
        cartService.clearCart();

    }

}
