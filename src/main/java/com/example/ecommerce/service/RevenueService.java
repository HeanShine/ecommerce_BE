package com.example.ecommerce.service;

import com.example.ecommerce.model.Account;
import com.example.ecommerce.model.Revenue;

import java.util.List;

public interface RevenueService extends IService<Revenue>{
    void addMonthlyRevenue(Account account);
    List<Revenue> findByAccount(Account account);
    List<Revenue> getYearlyRevenue(Account account);
}
