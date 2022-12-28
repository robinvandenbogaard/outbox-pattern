package com.robinthedev.todo.rest;

import com.robinthedev.todo.core.domain.TodoError;

public record RError(String message) {
    public RError(TodoError error) {
        this(error.message());
    }
}
