package ru.khorolskii.lesson_7.lesson_7.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProductToCartDto {

    private Long id;

    private String title;

    private int price;
}
