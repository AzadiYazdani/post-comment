package com.sale.baseinfo.exception;

public class BusinessTypeNotFoundException extends RuntimeException {

    public BusinessTypeNotFoundException(Long id) {
        super(String.format("No cities were found for the state with id %d", id));
    }

    public BusinessTypeNotFoundException() {
        super("No city found");
    }

}
