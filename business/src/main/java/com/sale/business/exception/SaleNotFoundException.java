package com.sale.business.exception;

public class SaleNotFoundException extends RuntimeException {

    public SaleNotFoundException() {
        super("No sale found");
    }

    public SaleNotFoundException(long id) {
        super(String.format("No sale found with id %d", id));
    }
}
