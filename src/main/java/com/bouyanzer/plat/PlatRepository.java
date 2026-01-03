package com.bouyanzer.plat;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface PlatRepository extends JpaRepository<Plat, Integer> {
    List<Plat> findByCategory(String category);

    List<Plat> findByRestaurantId(Integer restaurantID);

    List<Plat> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    List<Plat> findByName(String name);

}
