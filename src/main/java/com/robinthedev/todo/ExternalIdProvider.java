package com.robinthedev.todo;

import com.robinthedev.todo.domain.ExternalId;

public interface ExternalIdProvider {
    ExternalId next();
}
