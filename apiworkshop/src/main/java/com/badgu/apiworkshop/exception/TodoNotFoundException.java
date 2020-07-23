package com.badgu.apiworkshop.exception;

public class TodoNotFoundException extends RuntimeException {
    private Long id;

    public TodoNotFoundException(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
