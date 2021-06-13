package com.companiya.sales.domain.exceptions;

import lombok.AllArgsConstructor;
import java.io.Serializable;

@AllArgsConstructor
public class NoContentException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 2L;

}
