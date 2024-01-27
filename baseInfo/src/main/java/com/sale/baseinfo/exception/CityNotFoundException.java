package com.sale.baseinfo.exception;

public class CityNotFoundException extends RuntimeException {

    public CityNotFoundException(int id) {
        super(String.format("No cities were found for the state with id %d", id));
    }

    public CityNotFoundException() {
        super("No city found");
    }

}
