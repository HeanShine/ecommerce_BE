package com.example.ecommerce.repository;

import com.example.ecommerce.model.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAccountRepo extends CrudRepository<Account, Integer> {
    Account findByUsername(String username);
    @Query(value = "select * from account", nativeQuery = true)
    List<Account> findAll();
}
