package com.robinthedev.outbox.rest;

import com.robinthedev.outbox.todos.domain.TodoError;

public record RError(String message) {
    public RError(TodoError error) {
        this(error.message());
    }
}
