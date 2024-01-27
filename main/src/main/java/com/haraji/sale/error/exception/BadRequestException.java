package com.haraji.sale.error.exception;

public class BadRequestException extends BaseException {

    public BadRequestException() {
        super("the received request was not valid ");
    }

}
