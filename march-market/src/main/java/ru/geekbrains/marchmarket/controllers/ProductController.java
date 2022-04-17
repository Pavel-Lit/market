package ru.geekbrains.marchmarket.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.marchmarket.converters.CartConverter;
import ru.geekbrains.marchmarket.converters.ProductConverter;
import ru.geekbrains.marchmarket.dtos.CartDto;
import ru.geekbrains.marchmarket.dtos.ProductDto;
import ru.geekbrains.marchmarket.entities.Product;
import ru.geekbrains.marchmarket.exceptions.ResourceNotFoundException;
import ru.geekbrains.marchmarket.services.CartService;
import ru.geekbrains.marchmarket.services.ProductService;
import ru.geekbrains.marchmarket.utils.Cart;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;
    private final ProductConverter productConverter;
    private final CartConverter cartConverter;
    private final CartService cartService;


//    @GetMapping("")
//    public List<Product> getAllProducts(){
//        return productService.findAll();
//    }
    @GetMapping("")
    public List<ProductDto> getAllProducts(){
        return productConverter.entityToListProduct(productService.findAll());
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id){
        return productConverter.entityToDto(productService.findById(id).orElseThrow(()-> new ResourceNotFoundException("Продукт с id: "+id+" не найден")));
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id){

        productService.deleteById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewProducts(@RequestBody ProductDto productDto) {
        productService.createNewProduct(productDto);
    }

    @GetMapping("/test")
    public CartDto getCurrentCartDto(Cart cart) {
        return cartConverter.getCartDto(cartService.getCurrentCart());
    }

}
