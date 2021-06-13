package com.companiya.sales.domain.query;

public interface QueryHandler<R, Q> {

    R handle(final Q query);

}
