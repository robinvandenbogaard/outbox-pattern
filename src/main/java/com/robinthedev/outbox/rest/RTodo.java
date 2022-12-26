package com.robinthedev.outbox.rest;

import com.robinthedev.outbox.todos.domain.Todo;

import java.time.LocalDateTime;

public record RTodo(String summary, LocalDateTime createdAt, LocalDateTime completedAt) {
    public RTodo(Todo todo) {
        this(todo.summary().summary(), todo.createdAt(), todo.completedAt());
    }
}
