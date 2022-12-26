package com.robinthedev.outbox.todos;

import com.robinthedev.outbox.todos.domain.ExternalId;
import java.util.UUID;

public class FakeIdGenerator implements ExternalIdProvider {
    public static final ExternalId DEFAULT_ID =
            new ExternalId(UUID.fromString("75d90170-f521-42c4-87d5-262a6b5d358f"));
    private ExternalId next;

    @Override
    public ExternalId next() {
        return next != null ? next : DEFAULT_ID;
    }

    public void setNext(ExternalId next) {
        this.next = next;
    }
}
