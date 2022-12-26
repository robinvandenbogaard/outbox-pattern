package com.robinthedev.outbox.rest;

import com.robinthedev.outbox.todos.domain.Todo;
import java.time.LocalDateTime;
import java.util.UUID;

public record RTodo(UUID id, String summary, LocalDateTime createdAt, LocalDateTime completedAt) {
    public RTodo(Todo todo) {
        this(
                todo.externalId().uuid(),
                todo.summary().summary(),
                todo.createdAt(),
                todo.completedAt());
    }
}
