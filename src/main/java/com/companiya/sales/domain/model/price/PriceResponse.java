package com.companiya.sales.domain.model.price;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class PriceResponse {

    private Integer productId;
    private Integer brandId;
    private Integer priceId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BigDecimal price;
}
