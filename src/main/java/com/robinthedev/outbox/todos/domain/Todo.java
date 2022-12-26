package com.robinthedev.outbox.todos.domain;

import java.time.LocalDateTime;

public record Todo(
        ExternalId externalId,
        LocalDateTime createdAt,
        Summary summary,
        LocalDateTime completedAt) {
    public static Todo incomplete(ExternalId externalId, LocalDateTime createdAt, Summary summary) {
        return new Todo(externalId, createdAt, summary, null);
    }

    public boolean isCompleted() {
        return completedAt != null;
    }
}
