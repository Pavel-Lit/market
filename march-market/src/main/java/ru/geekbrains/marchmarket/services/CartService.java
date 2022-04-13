package ru.geekbrains.marchmarket.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.marchmarket.entities.Product;
import ru.geekbrains.marchmarket.exceptions.ResourceNotFoundException;
import ru.geekbrains.marchmarket.utils.Cart;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductService productService;
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
        Product p = productService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Продукт с id: " + productId + " не найден"));
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