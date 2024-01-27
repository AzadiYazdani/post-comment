package com.haraji.sale.error.exception;

public class PersistException extends BaseException {

    public PersistException(Long id) {
        super(String.format("the post %d not found", id));
    }

    public PersistException(Object obj) {
        super(String.format("An exception occurred in saving {}", obj));
    }

}
