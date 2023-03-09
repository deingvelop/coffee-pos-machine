package com.demo.coffeePosMachine.beverage;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface PopularBeverageRepository extends CrudRepository<PopularBeverage, Long> {
//    @Query(value = "SELECT o.beverage_id, o.beverage_name, o.beverage_price, count(*) as count " +
//            "FROM `order` o " +
//            "WHERE o.created_at >= date_format(now() - 7, '%YYYY-MM-DD HH:MM:SS') " +
//            "GROUP BY o.beverage_id, o.beverage_name, o.beverage_price " +
//            "ORDER BY count(*) desc " +
//            "LIMIT 3;",
//            nativeQuery = true)
//    List<PopularBeverage> findPopularsWithCache();

//    List<PopularBeverage> findPopularBeverageByCachedDate(LocalDate localDate);
    List<PopularBeverage> findAllByCachedDate(LocalDate localDate);

    List<PopularBeverage> findPopularBeverageByCachedDate(LocalDate now);
}
