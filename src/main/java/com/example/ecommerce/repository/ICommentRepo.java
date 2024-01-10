package com.example.ecommerce.repository;

import com.example.ecommerce.model.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.relational.core.sql.In;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICommentRepo extends CrudRepository<Comment,Integer> {
    @Query(value = "select * from comment where product_id = ?1",nativeQuery = true)
    List<Comment> findCommentByProductId(Integer id);

}
