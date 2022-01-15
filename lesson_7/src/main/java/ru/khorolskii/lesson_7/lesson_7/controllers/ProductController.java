package ru.khorolskii.lesson_7.lesson_7.controllers;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.khorolskii.lesson_7.lesson_7.dto.NewProductDto;
import ru.khorolskii.lesson_7.lesson_7.dto.ProductDto;
import ru.khorolskii.lesson_7.lesson_7.entities.Product;
import ru.khorolskii.lesson_7.lesson_7.exceptions.ResourceNotFoundExceptions;
import ru.khorolskii.lesson_7.lesson_7.services.ProductService;


import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
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
        return productService.find(minPrice, maxPrice, title, page).map(p -> new ProductDto(p));
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id).orElseThrow(() -> new ResourceNotFoundExceptions("Product not found, id: " + id));}

    @PostMapping
    public Product addProduct(@RequestBody NewProductDto newProductDto){
        return productService.save(newProductDto);
    }

    @PutMapping
    public Product updateProduct(@RequestBody NewProductDto newProductDto){
        return productService.save(newProductDto);
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

    @GetMapping("/more_price/{price}")
    public List<Product> getMorePrice(@PathVariable Integer price) {
        return productService.getAllMorePrice(price);
    }

    @GetMapping("/less_price/{price}")
    public List<Product> getLessPrice(@PathVariable Integer price){
        return  productService.getAllLessPrice(price);
    }
}
