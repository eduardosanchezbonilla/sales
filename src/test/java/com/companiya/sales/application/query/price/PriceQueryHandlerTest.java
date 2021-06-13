package com.companiya.sales.application.query.price;

import com.companiya.sales.domain.exceptions.NoContentException;
import com.companiya.sales.domain.mappers.Mapper;
import com.companiya.sales.domain.model.price.PriceRequest;
import com.companiya.sales.domain.model.price.PriceResponse;
import com.companiya.sales.domain.services.price.PriceService;
import com.companiya.sales.infraestructure.persistence.jpa.entities.prices.Prices;
import com.companiya.sales.infraestructure.persistence.jpa.entities.prices.PricesPK;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceQueryHandlerTest {

    @Mock
    private PriceService priceService;

    @Mock
    private Mapper<Prices, PriceResponse> responseAdapter;

    private PriceQueryHandler handler;

    @BeforeEach
    void setUp() {
        handler = new PriceQueryHandler(priceService,responseAdapter);
    }

    private PriceQuery getQuery(LocalDateTime applicationDate, Integer brandId, Integer productId){
        PriceRequest filters = new PriceRequest();
        filters.setApplicationDate(applicationDate);
        filters.setBrandId(brandId);
        filters.setProductId(productId);
        PriceQuery query = new PriceQuery(filters);
        return query;
    }

    private Prices getPrice(LocalDateTime startDate,
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
        return prices;
    }

    private PriceResponse getPriceResponse(LocalDateTime startDate,
                                            LocalDateTime endDate,
                                            Integer brandId,
                                            Integer productId,
                                            Integer priceList,
                                            BigDecimal price
    ){
        PriceResponse priceResponse = new PriceResponse();
        priceResponse.setStartDate(startDate);
        priceResponse.setEndDate(endDate);
        priceResponse.setBrandId(brandId);
        priceResponse.setProductId(productId);
        priceResponse.setPrice(price);
        priceResponse.setPriceId(priceList);
        return priceResponse;
    }

    @Test
    void handle() {
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
        PriceQuery query = getQuery(applicationDate,brandId,productId);
        Prices prices = getPrice(startDate,endDate,brandId,productId,priceList,curr,priority,price);
        PriceResponse priceResponse = getPriceResponse(startDate,endDate,brandId,productId,priceList,price);

        // when
        when(priceService.getPrice(query.getFilters())).thenReturn(prices);
        when(responseAdapter.mapTo(prices)).thenReturn(priceResponse);

        // call
        PriceResponse result = handler.handle(query);

        // assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getBrandId(),brandId);
        Assertions.assertEquals(result.getPriceId(),priceList);
        Assertions.assertEquals(result.getPrice(),price);
        Assertions.assertEquals(result.getProductId(),productId);
        Assertions.assertEquals(result.getStartDate(),startDate);
        Assertions.assertEquals(result.getEndDate(),endDate);
    }

    @Test
    void handleNoContent() {
        Assertions.assertThrows(NoContentException.class, () -> {
            // setup
            LocalDateTime applicationDate = LocalDateTime.now();
            Integer brandId = 1;
            Integer productId = 1;
            PriceQuery query = getQuery(applicationDate,brandId,productId);
            Prices prices = null;

            // when
            when(priceService.getPrice(query.getFilters())).thenReturn(prices);

            // call
            handler.handle(query);
        });
    }

}