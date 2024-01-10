package com.example.ecommerce.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailResponse {
    private int idProduct;
    private String nameProduct;
    private String imageProduct;
    private int quantity;
    private double price;
    private double total;
}
