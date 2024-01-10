package com.example.ecommerce.repository;

import com.example.ecommerce.model.Shop;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IShopRepo extends CrudRepository<Shop, Integer> {
    @Query(value = "select * from shop where account_id = ?1", nativeQuery = true)
    List<Shop> findByAccount(int id);

    // tim kiem Shop theo id
    @Query(value = "select * from shop where id = ?1", nativeQuery = true)
    Shop findByIdShop(int id);
}
