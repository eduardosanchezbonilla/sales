package com.companiya.sales.infraestructure.services.price;

import com.companiya.sales.domain.model.price.PriceRequest;
import com.companiya.sales.domain.services.price.PriceService;
import com.companiya.sales.infraestructure.persistence.jpa.entities.prices.Prices;
import com.companiya.sales.infraestructure.persistence.jpa.repositories.PricesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class PriceServiceImpl implements PriceService {

    private PricesRepository pricesRepository;

    public Prices getPrice(PriceRequest filters){
        List<Prices> prices = pricesRepository.findByBrandIdAndProductIdAndApplicationDate(filters.getApplicationDate(),filters.getProductId(),filters.getBrandId());
        if(prices!=null && Boolean.FALSE.equals(prices.isEmpty())){
            return  prices.stream().
                    sorted(Comparator.comparingInt(Prices::getPriority).reversed()).
                    collect(Collectors.toList()).get(0);
        }
        return null;
    }

}
