package ru.geekbrains.march.market.cart.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.march.market.api.CartDto;
import ru.geekbrains.march.market.cart.converters.CartConverter;
import ru.geekbrains.march.market.cart.services.CartService;
import ru.geekbrains.march.market.cart.utils.Cart;


@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final CartConverter cartConverter;


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
