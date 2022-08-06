package com.tecnotree.demo.error.exception;

public class CommentsNotFoundException extends BaseException {

    public CommentsNotFoundException(Long id) {
        super(String.format("No comments were found for the post with id %d", id));
    }

    public CommentsNotFoundException() {
        super(String.format("No comments found"));
    }

}
