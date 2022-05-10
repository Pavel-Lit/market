package ru.geekbrains.march.market.cart.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.march.market.api.CartDto;
import ru.geekbrains.march.market.api.StringResponse;
import ru.geekbrains.march.market.cart.converters.CartConverter;
import ru.geekbrains.march.market.cart.services.CartService;
import ru.geekbrains.march.market.cart.utils.Cart;

import java.util.UUID;


@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final CartConverter cartConverter;

    @GetMapping("/generate_id")
    public StringResponse generateCartId(){
        return new StringResponse(UUID.randomUUID().toString());
    }

    @GetMapping("/{guestCartId}")
    public CartDto getCurrentCartDto(@RequestHeader(required = false) String username, @PathVariable String guestCartId) {
        String currentCartId = selectCartId(username, guestCartId);
        return cartConverter.getCartDto(cartService.getCurrentCart(currentCartId));
    }

    @GetMapping("/{guestCartId}/add/{productId}")
    public void addProductToCart(@RequestHeader(required = false) String username, @PathVariable String guestCartId, @PathVariable Long productId) {
        String currentCartId = selectCartId(username, guestCartId);
        cartService.addToCart(currentCartId, productId);
    }

    @GetMapping("/{guestCartId}/remove/{id}")
    public void removeOneProductFromCart(@RequestHeader(required = false) String username, @PathVariable String guestCartId,@PathVariable Long id) {
        String currentCartId = selectCartId(username, guestCartId);
        cartService.removeById(currentCartId, id);
    }

    @GetMapping("/{guestCartId}/clear")
    public void clearCart(@RequestHeader(required = false) String username, @PathVariable String guestCartId) {
        String currentCartId = selectCartId(username, guestCartId);
        cartService.clearCart(currentCartId);

    }



    private String selectCartId(String username, String guestCartId){
        if(username != null){
            return username;
        }
        return guestCartId;
    }
}
