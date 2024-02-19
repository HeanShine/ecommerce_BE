package com.example.ecommerce.repository;

import com.example.ecommerce.model.Orders;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OderRepo extends CrudRepository<Orders, Integer> {
    @Query(value = "select * from orders where account_id = ?1 order by datetime desc", nativeQuery = true)
    List<Orders> findOderByAccount(int idAccount);

    @Query(value = "sum(total_payment) from orders where month(datetime) = ?1 or week(datetime) = ?2", nativeQuery = true)
    double getRevenueByMonthAndWeek(@Param("month") int month, @Param("week") int week);

    @Query(value = "select month(datetime) as month,sum(total_payment) as total" +
            " from orders where year(datetime) = :dateTime" +
            " group by month(datetime) order by month(datetime)", nativeQuery = true)
    List<Object[]> getRevenueByYear(@Param("dateTime") int dateTime);


}
