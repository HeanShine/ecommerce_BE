package com.example.ecommerce.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT", nullable = true)
    private String imageShop;

    @Column(nullable = true)
    private String apartmentNumber;

    @Column(nullable = true)
    private String ward;

    @Column(nullable = true)
    private String district;

    @Column(nullable = true)
    private String province;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
