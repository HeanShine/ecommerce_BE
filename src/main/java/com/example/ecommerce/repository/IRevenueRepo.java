package com.example.ecommerce.repository;

import com.example.ecommerce.model.Account;
import com.example.ecommerce.model.Revenue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IRevenueRepo extends JpaRepository<Revenue, Integer> {
    List<Revenue> findByAccount(Account account);
    boolean existsByAccountAndTimeBetween(Account account, Date start, Date end);
    Revenue findByAccountAndTimeBetween(Account account, Date start, Date end);
    List<Revenue> findAllByAccountAndTimeBetween(Account account, Date start, Date end);
}
