package ru.khorolskii.lesson_7.lesson_7.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.khorolskii.lesson_7.lesson_7.converter.ProductConverter;
import ru.khorolskii.lesson_7.lesson_7.dto.ProductDto;
import ru.khorolskii.lesson_7.lesson_7.entities.Product;
import ru.khorolskii.lesson_7.lesson_7.exceptions.ResourceNotFoundExceptions;
import ru.khorolskii.lesson_7.lesson_7.services.ProductService;


import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductConverter productConverter;

    @GetMapping
    public Page <ProductDto> filterProducts(
            @RequestParam (name = "min_price", required = false) Integer minPrice,
            @RequestParam (name = "max_price", required = false) Integer maxPrice,
            @RequestParam (name = "title", required = false) String title,
            @RequestParam (name = "p", defaultValue = "1") Integer page
    ){
        if (page <1) {
            page = 1;
        }
        return productService.find(minPrice, maxPrice, title, page).map(p -> productConverter.conversionToDto(p));
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id).orElseThrow(() -> new ResourceNotFoundExceptions("Product not found, id: " + id));
        return productConverter.conversionToDto(product);
    }

    @PostMapping
    public ProductDto addProduct(@RequestBody ProductDto ProductDto){
        Product product = productConverter.conversionToEntity(ProductDto);
        productService.save(product);
        return productConverter.conversionToDto(product);
    }

    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto productDto){
        Product product = productService.update(productDto);
        return productConverter.conversionToDto(product);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @GetMapping("/change_price")
    public void changePrice(@RequestParam Long productId, @RequestParam Integer delta) {
        productService.changePrice(productId, delta);
    }

//    @GetMapping("/products/price_between")
//    public List<Product> getPriceBetween(@RequestParam(defaultValue = "0") Integer min, @RequestParam(defaultValue = "9999") Integer max) {
//        return productService.getAllPriceBetween(min, max);
//    }
//
//    @GetMapping("/more_price/{price}")
//    public List<Product> getMorePrice(@PathVariable Integer price) {
//        return productService.getAllMorePrice(price);
//    }
//
//    @GetMapping("/less_price/{price}")
//    public List<Product> getLessPrice(@PathVariable Integer price){
//        return  productService.getAllLessPrice(price);
//    }
}
