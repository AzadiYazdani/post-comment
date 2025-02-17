package com.haraji.baseinfo.exception;

public class BusinessTypeNotFoundException extends RuntimeException {

    public BusinessTypeNotFoundException(Long id) {
        super(String.format("the business type %d not found", id));
    }

    public BusinessTypeNotFoundException() {
        super("No business type found");
    }

}
