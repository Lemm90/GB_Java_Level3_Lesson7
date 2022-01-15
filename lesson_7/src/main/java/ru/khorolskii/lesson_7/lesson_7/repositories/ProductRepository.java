package ru.khorolskii.lesson_7.lesson_7.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.khorolskii.lesson_7.lesson_7.entities.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    List<Product> findAllByPriceBetween(Integer min, Integer max);

    @Query("select p from Product p where p.price > :price")
    List<Product> getMorePrice(Integer price);

    @Query("select p from Product p where p.price < :price")
    List<Product> getAllLessPrice(Integer price);

    @Query("select p from Product p where p.price > :numberMin and p.price < :numberMax")
    List<Product> getAllFilteredProducts(Integer numberMin, Integer numberMax);
}
