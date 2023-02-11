package com.demo.coffeePosMachine.repository;

import com.demo.coffeePosMachine.entity.Beverage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BeverageRepository extends JpaRepository<Beverage, Long> {

    @Query(value = "SELECT * FROM beverage b " +
                        "INNER JOIN (SELECT o.beverage_id, count(*) `count` " +
                                "FROM `order` o " +
                                "WHERE o.created_at >= date_format(now() - 7, '%YYYY-MM-DD HH:MM:SS')\n" +
                                "GROUP BY o.beverage_id " +
                                "ORDER BY count(*) desc " +
                                ") as j " +
                        "on j.beverage_id = b.id " +
                        "LIMIT 3;",
                        nativeQuery = true)
    List<Beverage> findFavorites();
}
