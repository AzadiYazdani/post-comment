package com.haraji.baseinfo.exception.authentication;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(int id) {
        super(String.format("the user %d not found", id));
    }

    public UserNotFoundException() {
        super("No user found");
    }

}
