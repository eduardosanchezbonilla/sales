package com.companiya.sales.infraestructure.services.price;

import com.companiya.sales.domain.model.price.PriceRequest;
import com.companiya.sales.infraestructure.persistence.jpa.entities.prices.Prices;
import com.companiya.sales.infraestructure.persistence.jpa.entities.prices.PricesPK;
import com.companiya.sales.infraestructure.persistence.jpa.repositories.PricesRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceServiceImplTest {

    @Mock
    private PricesRepository pricesRepository;

    private PriceServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new PriceServiceImpl(pricesRepository);
    }

    private PriceRequest getFilters(LocalDateTime applicationDate, Integer brandId, Integer productId){
        PriceRequest filters = new PriceRequest();
        filters.setApplicationDate(applicationDate);
        filters.setBrandId(brandId);
        filters.setProductId(productId);
        return filters;
    }

    private List<Prices> getPrice(LocalDateTime startDate,
                                  LocalDateTime endDate,
                                  Integer brandId,
                                  Integer productId,
                                  Integer priceList,
                                  String curr,
                                  Integer priority,
                                  BigDecimal price
    ){
        PricesPK pricesPK = new PricesPK();
        pricesPK.setId(1);
        Prices prices = new Prices();
        prices.setId(pricesPK);
        prices.setStartDate(startDate);
        prices.setEndDate(endDate);
        prices.setPriority(priority);
        prices.setPrice(price);
        prices.setCurr(curr);
        prices.setProductId(productId);
        prices.setBrandId(brandId);
        prices.setPriceList(priceList);
        List<Prices> listPrices = new ArrayList<>();
        listPrices.add(prices);
        return listPrices;
    }

    @Test
    void getPrice() {
        // setup
        LocalDateTime applicationDate = LocalDateTime.now();
        LocalDateTime startDate = applicationDate.minusDays(1);
        LocalDateTime endDate = applicationDate.plusDays(1);
        Integer brandId = 1;
        Integer productId = 1;
        Integer priceList = 1;
        String curr = "curr";
        Integer priority = 1;
        BigDecimal price = BigDecimal.valueOf(35.2);
        PriceRequest filters = getFilters(applicationDate,brandId,productId);
        List<Prices> prices = getPrice(startDate,endDate,brandId,productId,priceList,curr,priority,price);

        // when
        when(pricesRepository.findByBrandIdAndProductIdAndApplicationDate(applicationDate,productId,brandId)).thenReturn(prices);

        // call
        Prices result = service.getPrice(filters);

        // assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getBrandId(),brandId);
        Assertions.assertEquals(result.getPrice(),price);
        Assertions.assertEquals(result.getProductId(),productId);
        Assertions.assertEquals(result.getStartDate(),startDate);
        Assertions.assertEquals(result.getEndDate(),endDate);
    }

    @Test
    void getPriceNullResult() {
        // setup
        LocalDateTime applicationDate = LocalDateTime.now();
        Integer brandId = 1;
        Integer productId = 1;
        PriceRequest filters = getFilters(applicationDate,brandId,productId);
        List<Prices> prices = null;

        // when
        when(pricesRepository.findByBrandIdAndProductIdAndApplicationDate(applicationDate,productId,brandId)).thenReturn(prices);

        // call
        Prices result = service.getPrice(filters);

        // assert
        Assertions.assertNull(result);
    }

    @Test
    void getPriceEmptyResult() {
        // setup
        LocalDateTime applicationDate = LocalDateTime.now();
        Integer brandId = 1;
        Integer productId = 1;
        PriceRequest filters = getFilters(applicationDate,brandId,productId);
        List<Prices> prices = new ArrayList<>();

        // when
        when(pricesRepository.findByBrandIdAndProductIdAndApplicationDate(applicationDate,productId,brandId)).thenReturn(prices);

        // call
        Prices result = service.getPrice(filters);

        // assert
        Assertions.assertNull(result);
    }
}