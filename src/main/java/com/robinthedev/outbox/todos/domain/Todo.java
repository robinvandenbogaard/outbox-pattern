package com.robinthedev.outbox.todos.domain;

import java.time.LocalDateTime;

public record Todo(LocalDateTime createdAt, Summary summary, LocalDateTime completedAt) {
    public static Todo incomplete(LocalDateTime createdAt, Summary summary) {
        return new Todo(createdAt, summary, null);
    }

    public boolean isCompleted() {
        return completedAt != null;
    }
}
