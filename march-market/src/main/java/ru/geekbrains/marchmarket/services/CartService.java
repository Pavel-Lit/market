package ru.geekbrains.marchmarket.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.marchmarket.entities.Product;

import java.util.ArrayList;
import java.util.List;
@Service
@Data
@AllArgsConstructor
public class CartService {



    private final ProductService productService;
    private List<Product> productList;



    public Product addToCart(Long id){
        Product product = productService.findById(id);
        productList.add(product);
        return product;
    }

    public List<Product> getAllCart(){
        return productList;
    }


}
