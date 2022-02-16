package ru.khorolskii.lesson_7.lesson_7.converter;

import org.springframework.stereotype.Component;
import ru.khorolskii.lesson_7.lesson_7.dto.ProductDto;
import ru.khorolskii.lesson_7.lesson_7.dto.ProductToCartDto;
import ru.khorolskii.lesson_7.lesson_7.entities.Product;

@Component
public class ProductConverter {

    public Product conversionToEntity(ProductDto productDto){
        Product product = new Product();
        product.setId(productDto.getId());
        product.setPrice(productDto.getPrice());
        product.setTitle(productDto.getTitle());
        return product;
    }

    public ProductDto conversionToDto(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setPrice(product.getPrice());
        productDto.setTitle(product.getTitle());
        return productDto;
    }

    public ProductToCartDto ProductToCartDto(Product product){
        ProductToCartDto productToCartDto = new ProductToCartDto();
        productToCartDto.setId(product.getId());
        productToCartDto.setPrice(product.getPrice());
        productToCartDto.setTitle(product.getTitle());
        return productToCartDto;
    }

}
