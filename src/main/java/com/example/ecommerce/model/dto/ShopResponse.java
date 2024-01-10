package com.example.ecommerce.model.dto;

import com.example.ecommerce.model.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopResponse {
    private int id;
    private String name;
    private String imageShop;
    private Account account;
}
