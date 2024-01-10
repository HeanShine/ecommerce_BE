package com.example.ecommerce.repository;

import com.example.ecommerce.model.OrderDetail;
import com.example.ecommerce.model.Orders;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OderRepo extends CrudRepository<Orders, Integer> {

    //desc la sap xep giam dan
    @Query(value = "select * from orders where account_id = ?1 order by datetime desc", nativeQuery = true)
    List<Orders> findOderByAccount(int idAccount);

}
