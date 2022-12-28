package com.robinthedev.outbox.domain;

import com.robinthedev.todo.events.EventType;

public record OutboxItem(OutboxId id, EventType type, Payload payload) {

    public String getSubject() {
        return type.name().toLowerCase();
    }

    public String getPayload() {
        return payload().jsonValue();
    }
}
