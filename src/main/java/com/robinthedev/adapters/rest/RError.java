package com.robinthedev.adapters.rest;

import com.robinthedev.todo.domain.TodoError;

public record RError(String message) {
    public RError(TodoError error) {
        this(error.message());
    }
}
