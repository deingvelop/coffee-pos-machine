package com.demo.coffeePosMachine.order;

import com.demo.coffeePosMachine.beverage.FavoriteBeverageDto;
import com.demo.coffeePosMachine.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "SELECT o.beverage_id, o.beverage_name, o.beverage_price, count(*) as count " +
            "FROM `order` o " +
            "WHERE o.created_at >= date_format(now() - 7, '%YYYY-MM-DD HH:MM:SS') " +
            "GROUP BY o.beverage_id, o.beverage_name, o.beverage_price " +
            "ORDER BY count(*) desc " +
            "LIMIT 3;",
            nativeQuery = true)
    List<FavoriteBeverageDto> findFavorites();

}