package com.robinthedev.outbox.todos.domain;

public record SaveError(String message, Exception cause) {
}
