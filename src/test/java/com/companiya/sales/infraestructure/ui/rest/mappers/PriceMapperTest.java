package com.companiya.sales.infraestructure.ui.rest.mappers;

import com.companiya.sales.domain.model.price.PriceResponse;
import com.companiya.sales.infraestructure.persistence.jpa.entities.prices.Prices;
import com.companiya.sales.infraestructure.persistence.jpa.entities.prices.PricesPK;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
class PriceMapperTest {

    private PriceMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new PriceMapper();
    }

    @Test
    void mapTo() {
        // setup
        Integer id = 1;
        Integer brandId = 1;
        Integer productId = 1;
        Integer priceList = 1;
        BigDecimal price = BigDecimal.valueOf(30.5);
        Integer priority = 1;
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.now().plusDays(5);
        String curr = "curr";
        //
        Prices actual = new Prices();
        PricesPK pk = new PricesPK();
        pk.setId(id);
        actual.setId(pk);
        actual.setBrandId(brandId);
        actual.setProductId(productId);
        actual.setCurr(curr);
        actual.setPriceList(priceList);
        actual.setPrice(price);
        actual.setPriority(priority);
        actual.setStartDate(startDate);
        actual.setEndDate(endDate);


        // call to method
        PriceResponse result = mapper.mapTo(actual);

        // assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getBrandId(),actual.getBrandId());

    }
}