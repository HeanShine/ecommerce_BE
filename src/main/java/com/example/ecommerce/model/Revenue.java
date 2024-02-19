package com.example.ecommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "revenue")
public class Revenue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date time;
    private double revenue;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public Revenue(double revenue, Date time, Account account) {
        this.revenue = revenue;
        this.time = time;
        this.account = account;
    }

}
