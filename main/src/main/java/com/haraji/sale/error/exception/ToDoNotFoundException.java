package com.haraji.sale.error.exception;

public class ToDoNotFoundException extends BaseException {

    public ToDoNotFoundException(Long id) {
        super(String.format("the todo %d not found", id));
    }

    public ToDoNotFoundException() {
        super(String.format("No todo found"));
    }
}
