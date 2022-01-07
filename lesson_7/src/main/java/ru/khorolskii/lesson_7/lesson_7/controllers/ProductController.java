package ru.khorolskii.lesson_7.lesson_7.controllers;

import ru.khorolskii.lesson_7.lesson_7.data.Product;
import ru.khorolskii.lesson_7.lesson_7.exceptions.ResourceNotFoundExceptions;
import ru.khorolskii.lesson_7.lesson_7.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

//    @GetMapping("/products")
//    public List<Product> getAllProducts() {
//        return productService.getAllProducts();
//    }

    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id).orElseThrow(() -> new ResourceNotFoundExceptions("Product not found, id: " + id));}

    @GetMapping("/products/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @GetMapping("/products/change_price")
    public void changePrice(@RequestParam Long productId, @RequestParam Integer delta) {
        productService.changePrice(productId, delta);
    }

    @GetMapping("/products/price_between")
    public List<Product> getPriceBetween(@RequestParam(defaultValue = "0") Integer min, @RequestParam(defaultValue = "9999") Integer max) {
        return productService.getAllPriceBetween(min, max);
    }

    @GetMapping("/products/more_price/{price}")
    public List<Product> getMorePrice(@PathVariable Integer price) {
        return productService.getAllMorePrice(price);
    }

    @GetMapping("/products/less_price/{price}")
    public List<Product> getLessPrice(@PathVariable Integer price){
        return  productService.getAllLessPrice(price);
    }

    @GetMapping("/products")
    public List <Product> filterProduct(@RequestParam (defaultValue = "0") Integer numberMin, @RequestParam (defaultValue = "9999") Integer numberMax){
        System.out.println("min: " + numberMin + " max: " + numberMax);
        return productService.getAllFilteredProducts(numberMin, numberMax);
    }

}
