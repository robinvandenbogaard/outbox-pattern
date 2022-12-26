package com.robinthedev.outbox.todos;

import com.robinthedev.outbox.todos.domain.ExternalId;

public interface ExternalIdProvider {
    ExternalId next();
}
