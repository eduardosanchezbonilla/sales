package com.companiya.sales.infraestructure.ui.rest.mappers;

import com.companiya.sales.domain.mappers.Mapper;
import com.companiya.sales.domain.model.price.PriceResponse;
import com.companiya.sales.infraestructure.persistence.jpa.entities.prices.Prices;
import org.springframework.stereotype.Component;

@Component
public class PriceMapper implements Mapper<Prices, PriceResponse> {

    @Override
    public PriceResponse mapTo(Prices origin) {
        PriceResponse priceResponse = new PriceResponse();
        priceResponse.setPriceId(origin.getPriceList());
        priceResponse.setBrandId(origin.getBrandId());
        priceResponse.setProductId(origin.getProductId());
        priceResponse.setPrice(origin.getPrice());
        priceResponse.setStartDate(origin.getStartDate());
        priceResponse.setEndDate(origin.getEndDate());
        return priceResponse;
    }
}
