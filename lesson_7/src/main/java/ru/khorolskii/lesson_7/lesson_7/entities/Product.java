package ru.khorolskii.lesson_7.lesson_7.entities;


import ru.khorolskii.lesson_7.lesson_7.dto.NewProductDto;

import javax.persistence.*;

@Entity
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "title")
    public String title;

    @Column(name = "price")
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

    public Product() {
    }

    public Product(NewProductDto newProductDto) {
        this.id = newProductDto.getId();
        this.title = newProductDto.getTitle();
        this.price = newProductDto.getPrice();
    }
}
