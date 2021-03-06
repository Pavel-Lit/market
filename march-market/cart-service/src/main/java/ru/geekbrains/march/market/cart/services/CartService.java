package ru.geekbrains.march.market.cart.services;

import lombok.RequiredArgsConstructor;
import ru.geekbrains.march.market.api.ProductDto;
import ru.geekbrains.march.market.cart.exceptions.ResourceNotFoundException;
import ru.geekbrains.march.market.cart.integrations.ProductServiceIntegration;
import ru.geekbrains.march.market.cart.utils.Cart;

import org.springframework.stereotype.Service;


import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductServiceIntegration productServiceIntegration;
    private Cart cart;

    @PostConstruct
    public void init() {
        cart = new Cart();
        cart.setItems(new ArrayList<>());

    }

    public Cart getCurrentCart() {
        return cart;
    }

    public void addToCart(Long productId) {
        ProductDto p = productServiceIntegration.findById(productId);
        cart.add(p);
    }




    public void clearCart(){
        cart.clear();
    }

    public void removeById(Long id) {
        cart.deleteProductFormCart(id);
    }


    public BigDecimal getTotalPrice(){
        return cart.getTotalPrice();
    }
}