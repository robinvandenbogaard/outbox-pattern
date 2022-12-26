package com.robinthedev.outbox.todos.domain;

public record TodoError(String message, Exception cause) {}
