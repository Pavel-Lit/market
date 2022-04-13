package ru.geekbrains.marchmarket.converters;

import org.springframework.stereotype.Component;
import ru.geekbrains.marchmarket.dtos.ProductDto;
import ru.geekbrains.marchmarket.entities.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductConverter {


//    private List<ProductDto> productDtoList;



//    public ProductConverter(List<ProductDto> productDtoList) {
//        this.productDtoList = productDtoList;
//    }

    public ProductDto entityToDto(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setTitle(product.getTitle());
        productDto.setPrice(product.getPrice());
        productDto.setCategoryTitle(product.getCategory().getTitle());
        return productDto;
    }

    public List<ProductDto> entityToListProduct(List<Product> productList){
        List<ProductDto> productDtoList = new ArrayList<>();

        for (int i = 0; i < productList.size(); i++) {
            productDtoList.add(entityToDto(productList.get(i)));

        }
        return productDtoList;
    }
}
