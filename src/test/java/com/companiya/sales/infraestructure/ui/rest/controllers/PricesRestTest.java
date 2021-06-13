package com.companiya.sales.infraestructure.ui.rest.controllers;

import com.companiya.sales.infraestructure.persistence.jpa.entities.prices.Prices;
import com.companiya.sales.infraestructure.persistence.jpa.entities.prices.PricesPK;
import com.companiya.sales.infraestructure.persistence.jpa.repositories.PricesRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application.yml")
@AutoConfigureMockMvc
class PricesRestTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PricesRepository pricesRepository;

    public LocalDateTime stringToLocalDateTime(String strDate) {
        return LocalDateTime.parse(strDate,DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
    }

    private List<Prices> getPrices(){
        List<Prices>  pricesList = new ArrayList<>();
        //
        PricesPK pricePK = new PricesPK();
        pricePK.setId(1);
        Prices price = new Prices();
        price.setId(pricePK);
        price.setStartDate(stringToLocalDateTime("2020-06-14T00:00:00"));
        price.setEndDate(stringToLocalDateTime("2020-12-31T23:59:59"));
        price.setPriority(0);
        price.setPrice(BigDecimal.valueOf(35.50));
        price.setCurr("EUR");
        price.setProductId(35455);
        price.setBrandId(1);
        price.setPriceList(1);
        pricesList.add(price);
        //
        pricePK = new PricesPK();
        pricePK.setId(2);
        price = new Prices();
        price.setId(pricePK);
        price.setStartDate(stringToLocalDateTime("2020-06-14T15:00:00"));
        price.setEndDate(stringToLocalDateTime("2020-06-14T18:30:00"));
        price.setPriority(1);
        price.setPrice(BigDecimal.valueOf(25.45));
        price.setCurr("EUR");
        price.setProductId(35455);
        price.setBrandId(1);
        price.setPriceList(2);
        pricesList.add(price);
        //
        pricePK = new PricesPK();
        pricePK.setId(3);
        price = new Prices();
        price.setId(pricePK);
        price.setStartDate(stringToLocalDateTime("2020-06-15T00:00:00"));
        price.setEndDate(stringToLocalDateTime("2020-06-15T11:00:00"));
        price.setPriority(1);
        price.setPrice(BigDecimal.valueOf(30.50));
        price.setCurr("EUR");
        price.setProductId(35455);
        price.setBrandId(1);
        price.setPriceList(3);
        pricesList.add(price);
        //
        pricePK = new PricesPK();
        pricePK.setId(4);
        price = new Prices();
        price.setId(pricePK);
        price.setStartDate(stringToLocalDateTime("2020-06-15T16:00:00"));
        price.setEndDate(stringToLocalDateTime("2020-12-31T23:59:59"));
        price.setPriority(1);
        price.setPrice(BigDecimal.valueOf(38.95));
        price.setCurr("EUR");
        price.setProductId(35455);
        price.setBrandId(1);
        price.setPriceList(4);
        pricesList.add(price);
        //
        return pricesList;
    }

    @BeforeEach
    public void init() {
        List<Prices> prices = getPrices();
        prices.stream().forEach(p->pricesRepository.save(p));
    }

    @AfterEach
    public void delete() {
        pricesRepository.deleteAll();
    }

    @Test
    public void prices20200614100000()throws Exception {
        // setup
        String applicationDate = "2020-06-14T10:00:00";
        String productId = "35455";
        String brandId = "1";
        BigDecimal expectedPrice = BigDecimal.valueOf(35.50);

        // call and assert
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/price")
                .queryParam("applicationDate",applicationDate)
                .queryParam("productId",productId)
                .queryParam("brandId",brandId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(expectedPrice))
                .andReturn();
    }

    @Test
    public void prices20200614160000()throws Exception {
        // setup
        String applicationDate = "2020-06-14T16:00:00";
        String productId = "35455";
        String brandId = "1";
        BigDecimal expectedPrice = BigDecimal.valueOf(25.45);

        // call and assert
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/price")
                .queryParam("applicationDate",applicationDate)
                .queryParam("productId",productId)
                .queryParam("brandId",brandId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(expectedPrice))
                .andReturn();
    }

    @Test
    public void prices20200614210000()throws Exception {
        // setup
        String applicationDate = "2020-06-14T21:00:00";
        String productId = "35455";
        String brandId = "1";
        BigDecimal expectedPrice = BigDecimal.valueOf(35.50);

        // call and assert
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/price")
                .queryParam("applicationDate",applicationDate)
                .queryParam("productId",productId)
                .queryParam("brandId",brandId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(expectedPrice))
                .andReturn();
    }

    @Test
    public void prices20200615100000()throws Exception {
        // setup
        String applicationDate = "2020-06-15T10:00:00";
        String productId = "35455";
        String brandId = "1";
        BigDecimal expectedPrice = BigDecimal.valueOf(30.50);

        // call and assert
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/price")
                .queryParam("applicationDate",applicationDate)
                .queryParam("productId",productId)
                .queryParam("brandId",brandId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(expectedPrice))
                .andReturn();
    }

    @Test
    public void prices20200616210000()throws Exception {
        // setup
        String applicationDate = "2020-06-16T21:00:00";
        String productId = "35455";
        String brandId = "1";
        BigDecimal expectedPrice = BigDecimal.valueOf(38.95);

        // call and assert
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/price")
                .queryParam("applicationDate",applicationDate)
                .queryParam("productId",productId)
                .queryParam("brandId",brandId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(expectedPrice))
                .andReturn();
    }

    @Test
    public void pricesNoContent()throws Exception {
        // setup
        String applicationDate = "2022-06-16T21:00:00";
        String productId = "35455";
        String brandId = "1";

        // call and assert
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/price")
                .queryParam("applicationDate",applicationDate)
                .queryParam("productId",productId)
                .queryParam("brandId",brandId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andReturn();
    }
}