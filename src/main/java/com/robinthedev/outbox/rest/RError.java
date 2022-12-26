package com.robinthedev.outbox.rest;

import com.robinthedev.outbox.todos.domain.SaveError;

public record RError(String message) {
    public RError(SaveError error) {
        this(error.message());
    }
}
