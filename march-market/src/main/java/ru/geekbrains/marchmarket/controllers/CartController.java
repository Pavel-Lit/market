package ru.geekbrains.marchmarket.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.marchmarket.entities.Product;
import ru.geekbrains.marchmarket.services.CartService;
import ru.geekbrains.marchmarket.services.ProductService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {

    private final CartService cartService;


    @GetMapping("")
    public List<Product> allCart() {
        return cartService.getAllCart();
    }


    @GetMapping ("/{id}")
    public void addToCart(@PathVariable Long id){

        cartService.addToCart(id);

    }



}
