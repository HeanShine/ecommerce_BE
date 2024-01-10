package com.example.ecommerce.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private int id;
    private String name;
    private String title;
    private float price;
    private String imageMainProduct;
    private int quantity;
    private String status;
    private String nameCategory;
}
