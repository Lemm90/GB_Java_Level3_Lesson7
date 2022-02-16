package ru.khorolskii.lesson_7.lesson_7.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.khorolskii.lesson_7.lesson_7.converter.ProductConverter;
import ru.khorolskii.lesson_7.lesson_7.dto.ProductToCartDto;
import ru.khorolskii.lesson_7.lesson_7.entities.Product;
import ru.khorolskii.lesson_7.lesson_7.exceptions.ResourceNotFoundExceptions;
import ru.khorolskii.lesson_7.lesson_7.repositories.ProductRepository;
import ru.khorolskii.lesson_7.lesson_7.entities.Cart;
import ru.khorolskii.lesson_7.lesson_7.services.CartService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor

public class CartController {
    private final ProductConverter productConverter;
    private final CartService cartService;
    private final ProductRepository productRepository;

    @GetMapping("/add")
    public ProductToCartDto addProduct(@RequestParam Long productId){
        log.debug("id продукта, который пришел с фронта: " + productId);
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundExceptions("Product not found, id: " + productId));
        log.debug("отправка DTO в корзину: " + product.toString());
        return cartService.addProduct(product);
    }

    @GetMapping("/remove")
    public void removeProduct(@RequestParam Long productId){
        cartService.removeProduct(productId);
    }

    @GetMapping("/show")
    public List <ProductToCartDto> showCart(){
       return cartService.showCart();
    }

    @GetMapping("/clear")
    public void clear(){
        cartService.clear();
    }
}
