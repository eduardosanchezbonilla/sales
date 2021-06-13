package com.companiya.sales.application.query.price;

import com.companiya.sales.domain.exceptions.NoContentException;
import com.companiya.sales.domain.mappers.Mapper;
import com.companiya.sales.domain.model.price.PriceResponse;
import com.companiya.sales.domain.query.QueryHandler;
import com.companiya.sales.domain.services.price.PriceService;
import com.companiya.sales.infraestructure.persistence.jpa.entities.prices.Prices;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PriceQueryHandler implements QueryHandler<PriceResponse, PriceQuery> {

    private PriceService priceService;

    private Mapper<Prices, PriceResponse> responseAdapter;

    @Override
    public PriceResponse handle(PriceQuery query) {
        Prices price = priceService.getPrice(query.getFilters());
        if(price!=null) {
            return responseAdapter.mapTo(priceService.getPrice(query.getFilters()));
        }
        else{
            throw new NoContentException();
        }
    }

}