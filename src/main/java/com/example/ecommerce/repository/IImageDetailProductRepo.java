package com.example.ecommerce.repository;

import com.example.ecommerce.model.ImageDetailProduct;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IImageDetailProductRepo extends CrudRepository<ImageDetailProduct, Integer> {
    @Query(value = "SELECT * FROM image_detail_product WHERE product_id = :idProduct", nativeQuery = true)
    List<ImageDetailProduct> findAllByIdProduct(int idProduct);
}
