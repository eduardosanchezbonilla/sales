package com.companiya.sales.application.query.price;

import com.companiya.sales.domain.model.price.PriceRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class PriceQuery {

    private PriceRequest filters;

}
