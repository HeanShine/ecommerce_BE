package com.example.ecommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private Date datetime;

    @Column(nullable = true)
    private Double totalPayment;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    public int getMonthFromTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.datetime);
        return calendar.get(Calendar.MONTH) + 1;
    }
}
