package com.haraji.sale.error.exception;

public class PostNotFoundException extends BaseException {

    public PostNotFoundException(Long id) {
        super(String.format("the post %d not found", id));
    }

    public PostNotFoundException() {
        super(String.format("No post found"));
    }

}
