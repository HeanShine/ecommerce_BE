package com.example.ecommerce.repository;

import com.example.ecommerce.model.OrderDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OderDetailRepo extends CrudRepository<OrderDetail, Integer> {
    @Query(value = "select * from order_detail where orders_id = ?1", nativeQuery = true)
    List<OrderDetail> findAllByIdOrder(int idOrder);
}
