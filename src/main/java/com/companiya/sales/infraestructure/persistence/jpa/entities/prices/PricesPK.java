package com.companiya.sales.infraestructure.persistence.jpa.entities.prices;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class PricesPK implements Serializable{

    @Column(name = "id")
    private Integer id;

}
