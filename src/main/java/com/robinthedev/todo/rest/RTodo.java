package com.robinthedev.todo.rest;

import com.robinthedev.todo.core.domain.Todo;
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
