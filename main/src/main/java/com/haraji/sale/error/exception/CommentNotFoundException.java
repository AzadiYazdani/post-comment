package com.haraji.sale.error.exception;

public class CommentNotFoundException extends BaseException {

    public CommentNotFoundException(Long id) {
        super(String.format("No comments were found for the post with id %d", id));
    }

    public CommentNotFoundException() {
        super(String.format("No comments found"));
    }

}
