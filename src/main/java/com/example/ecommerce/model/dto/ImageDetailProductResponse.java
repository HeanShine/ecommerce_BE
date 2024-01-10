package com.example.ecommerce.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageDetailProductResponse {
    private int id;
    private String imageDetailProduct;
    private int productId;
}
