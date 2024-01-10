package com.example.ecommerce.repository;

import com.example.ecommerce.model.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICategoryRepo extends CrudRepository<Category,Integer> {
    @Query(value = "SELECT * FROM category ORDER BY id DESC LIMIT 3", nativeQuery = true)
    List<Category> findNewCategory();
}
