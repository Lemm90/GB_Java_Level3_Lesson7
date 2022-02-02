package ru.khorolskii.lesson_7.lesson_7.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProductDto {

    private long id;

    public String title;

    public int price;

}
