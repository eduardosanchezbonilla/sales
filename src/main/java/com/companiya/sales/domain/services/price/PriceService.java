package com.companiya.sales.domain.services.price;

import com.companiya.sales.domain.model.price.PriceRequest;
import com.companiya.sales.infraestructure.persistence.jpa.entities.prices.Prices;

public interface PriceService {

    Prices getPrice(PriceRequest filters);

}
