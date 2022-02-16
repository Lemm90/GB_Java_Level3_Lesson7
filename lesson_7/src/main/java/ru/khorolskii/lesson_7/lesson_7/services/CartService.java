package ru.khorolskii.lesson_7.lesson_7.services;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.khorolskii.lesson_7.lesson_7.dto.ProductToCartDto;
import ru.khorolskii.lesson_7.lesson_7.entities.Cart;
import ru.khorolskii.lesson_7.lesson_7.entities.Product;


import java.util.List;

@Slf4j
@Service
@Data
@RequiredArgsConstructor
public class CartService {
    private final Cart cart;

    public ProductToCartDto addProduct(Product product){
        return cart.add(product);
    }

    public void removeProduct(Long productId){
        cart.remove(productId);
    }

    public List<ProductToCartDto> showCart(){
        return cart.showCart();
    }

    public void clear(){
        cart.clear();
    }
}
