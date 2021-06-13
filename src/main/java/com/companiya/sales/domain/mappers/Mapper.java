package com.companiya.sales.domain.mappers;

public interface Mapper<O, T> {

    T mapTo(O origin);

}
