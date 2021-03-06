package ru.geekbrains.march.market.core.controllers;

import lombok.RequiredArgsConstructor;
import ru.geekbrains.march.market.api.ProductDto;
import ru.geekbrains.march.market.core.converters.ProductConverter;
import ru.geekbrains.march.market.core.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import ru.geekbrains.march.market.core.services.ProductService;


import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;
    private final ProductConverter productConverter;


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

}
