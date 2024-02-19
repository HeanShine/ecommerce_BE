package com.example.ecommerce.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RevenueRes {
    private int month;
    private double total;

    public RevenueRes() {

    }
}
