package com.example.ecommerce.repository;

import com.example.ecommerce.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IProductRepo extends CrudRepository<Product, Integer> {
    List<Product> findAllByCategoryId(int idCategory);

    @Query(value = "SELECT * FROM product ORDER BY id DESC LIMIT 4", nativeQuery = true)
    List<Product> findNewProduct();

    @Query(value = "SELECT p.* FROM product p " +
            "WHERE (p.name LIKE %:nameSearch% or :nameSearch is null)" +
            "AND p.price between :minPrice and :maxPrice " +
            "AND p.status = 'ACTIVE' " +
            "ORDER BY p.id DESC", nativeQuery = true)
    List<Product> findAllByFilters
    (@Param("nameSearch") String nameSearch,
     @Param("minPrice") float minPrice,
     @Param("maxPrice") float maxPrice);

    @Query(value = "SELECT p.*, c.name as nameCategory " +
            "FROM product p INNER JOIN category c " +
            "ON p.category_id = c.id WHERE p.id = :idProduct", nativeQuery = true)
    Product findProductById(@Param("idProduct") int idProduct);

    @Query(value = "SELECT * FROM product where product.name like %:nameSearch% or :nameSearch is null", nativeQuery = true)
    List<Product> findProductByName(@Param("nameSearch") String nameSearch);

    @Query(value = "SELECT * FROM product where product.shop_id = :idShop", nativeQuery = true)
    List<Product> findProductByIdShop(@Param("idShop") int idShop);

    @Query(value = "INSERT INTO product (name, title, price, quantity, status, image_main_product, category_id)" +
            " VALUES (:name, :title, :price, :quantity, :status, :imageMainProduct, :categoryId)" +
            " WHERE product.shop_id = :idShop", nativeQuery = true)
    Product createProductByIdShop(@Param("idShop") int idShop);

    @Query(value = "SELECT * FROM product where product.id in (select product_id from order_detail where orders_id = ?1)", nativeQuery = true)
    List<Product> findProductByOrderDetail(int idOrder);
}
