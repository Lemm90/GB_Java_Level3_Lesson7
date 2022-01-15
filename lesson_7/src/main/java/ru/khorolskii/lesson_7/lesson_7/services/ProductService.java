package ru.khorolskii.lesson_7.lesson_7.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;
import ru.khorolskii.lesson_7.lesson_7.dto.NewProductDto;
import ru.khorolskii.lesson_7.lesson_7.entities.Product;
import org.springframework.stereotype.Service;
import ru.khorolskii.lesson_7.lesson_7.exceptions.ResourceNotFoundExceptions;
import ru.khorolskii.lesson_7.lesson_7.repositories.ProductRepository;
import ru.khorolskii.lesson_7.lesson_7.repositories.specifications.ProductsSpecifications;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository= productRepository;
    }

    public Page <Product> find(Integer minPrice, Integer maxPrice, String title, Integer page){
        System.out.println(minPrice);
        System.out.println(maxPrice);
        System.out.println(title);
        Specification <Product> spec = Specification.where(null);
        if(minPrice != null){
            spec = spec.and(ProductsSpecifications.priceGreaterOrEqualsThan(minPrice));
        }
        if(maxPrice != null){
            spec = spec.and(ProductsSpecifications.pricelessThanOrEqualThan(maxPrice));
        }
        if(title != null){
            spec = spec.and(ProductsSpecifications.titleLike(title));
        }
        return productRepository.findAll(spec, PageRequest.of(page - 1, 5));
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public Optional<Product> getProductById(Long id) {
        return  productRepository.findById(id);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Transactional
    public void changePrice(Long productId, Integer delta) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundExceptions("Unable to change product's price. Product not found, id: " + productId));
        product.setPrice(product.getPrice() + delta);
    }

    public List<Product> getAllPriceBetween(Integer min, Integer max) {
       return productRepository.findAllByPriceBetween(min, max);
    }

    public List <Product> getAllMorePrice(Integer price) {
        return productRepository.getMorePrice(price);
    }

    public List<Product> getAllLessPrice(Integer price) {
        return productRepository.getAllLessPrice(price);
    }

    public List<Product>getAllFilteredProducts(Integer numberMin, Integer numberMax){
        return productRepository.getAllFilteredProducts(numberMin, numberMax);
    }

    public Product save(NewProductDto newProductDto) {
        Product p = new Product(newProductDto);
        return productRepository.save(p);
    }

}
