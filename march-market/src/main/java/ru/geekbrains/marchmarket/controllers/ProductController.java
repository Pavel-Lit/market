package ru.geekbrains.marchmarket.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.marchmarket.dtos.CreateNewProductDto;
import ru.geekbrains.marchmarket.entities.Product;
import ru.geekbrains.marchmarket.services.CartService;
import ru.geekbrains.marchmarket.services.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    private final CartService cartService;


    @GetMapping("")
    public List<Product> getAllProducts(){
        return productService.findAll();
    }


    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id){

        productService.deleteById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewProducts(@RequestBody CreateNewProductDto createNewProductDto) {
        productService.createNewProduct(createNewProductDto);
    }


}
