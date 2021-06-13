package com.companiya.sales.infraestructure.persistence.jpa.repositories;

import com.companiya.sales.infraestructure.persistence.jpa.entities.prices.Prices;
import com.companiya.sales.infraestructure.persistence.jpa.entities.prices.PricesPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PricesRepository extends JpaRepository<Prices, PricesPK> {

    @Query("SELECT pri " +
           "FROM Prices pri " +
           "WHERE pri.startDate<=:applicationDate " +
              "AND pri.endDate>=:applicationDate " +
              "AND pri.productId=:productId " +
              "AND pri.brandId=:brandId " +
           "")
    List<Prices> findByBrandIdAndProductIdAndApplicationDate(@Param("applicationDate") LocalDateTime applicationDate,
                                                              @Param("productId") Integer productId,
                                                              @Param("brandId") Integer brandId
    );

}
