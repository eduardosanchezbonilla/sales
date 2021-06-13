package com.companiya.sales.domain.model.price;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class PriceRequest {
    private LocalDateTime applicationDate;
    private Integer productId;
    private Integer brandId;
}
