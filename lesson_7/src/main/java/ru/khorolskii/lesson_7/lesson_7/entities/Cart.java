package ru.khorolskii.lesson_7.lesson_7.entities;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.khorolskii.lesson_7.lesson_7.converter.ProductConverter;
import ru.khorolskii.lesson_7.lesson_7.dto.ProductToCartDto;
import ru.khorolskii.lesson_7.lesson_7.entities.Product;


import java.util.ArrayList;
import java.util.List;

@Slf4j
@Data
@RequiredArgsConstructor
@Component
public class Cart {

    private List <ProductToCartDto> cart = new ArrayList<>();
    private final ProductConverter productConverter;

    public ProductToCartDto add(Product product) {
        ProductToCartDto productToCartDto = productConverter.ProductToCartDto(product);
        cart.add(productToCartDto);
        log.debug("Добавлен продукт. Корзина: " + cart.toString());
        return productToCartDto;
    }
    public void remove (Long id){
        if(cart != null || cart.isEmpty()){
            for (int i = 0; i < cart.size(); i++) {
                if(cart.get(i).getId().equals(id)){
                    cart.remove(i);
                    log.debug("Продукт удален. Корзина: " + cart.toString());
                    break;
                }
            }
        }
    }

    public List<ProductToCartDto> showCart (){
        return cart;
    }

    public void clear(){
        cart.clear();
        log.debug("Корзина очищена. Size: " + cart.size());
    }


}
