package com.haraji.baseinfo.exception;

public class StateNotFoundException extends RuntimeException {

    public StateNotFoundException(int id) {
        super(String.format("the post %d not found", id));
    }

    public StateNotFoundException() {
        super("No state found");
    }

}
