package com.companiya.sales.infraestructure.ui.rest.controllers;

import com.companiya.sales.application.query.price.PriceQuery;
import com.companiya.sales.domain.model.price.PriceRequest;
import com.companiya.sales.domain.model.price.PriceResponse;
import com.companiya.sales.domain.query.QueryHandler;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@AllArgsConstructor
@RequestMapping(value = "${app-context-path}/price", consumes = "application/json; charset=UTF-8" , produces = "application/json; charset=UTF-8" )
public class PricesRest {

    private final QueryHandler<PriceResponse, PriceQuery> queryHandler;

    @GetMapping
    public PriceResponse price(@RequestParam("applicationDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate,
                               @RequestParam("productId") Integer productId,
                               @RequestParam("brandId") Integer brandId
    ) {
        PriceRequest filters = new PriceRequest();
        filters.setApplicationDate(applicationDate);
        filters.setProductId(productId);
        filters.setBrandId(brandId);
        return this.queryHandler.handle(new PriceQuery(filters));
    }
}
