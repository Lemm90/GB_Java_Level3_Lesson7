package ru.khorolskii.lesson_7.lesson_7.dto;


import ru.khorolskii.lesson_7.lesson_7.entities.Product;

import javax.persistence.*;

public class ProductDto {

    private long id;

    public String title;

    public int price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public ProductDto() {
    }

    public ProductDto(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
    }
}