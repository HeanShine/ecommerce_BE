package com.example.ecommerce.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
    private String name;
    private String title;
    private float price;
    private String imageMainProduct;
    private int quantity;
    private String status;
    private int idCategory;
    private int idShop;
}
