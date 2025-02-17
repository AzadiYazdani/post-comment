package com.haraji.baseinfo.exception.authentication;

public class UserNotCreatedException extends RuntimeException {

    public UserNotCreatedException() {
        super("No user created");
    }

}
